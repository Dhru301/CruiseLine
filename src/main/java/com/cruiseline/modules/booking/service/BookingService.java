package com.cruiseline.modules.booking.service;

import com.cruiseline.common.audit.AuditService;
import com.cruiseline.common.dto.PagedResponse;
import com.cruiseline.common.enums.*;
import com.cruiseline.exception.BusinessRuleException;
import com.cruiseline.exception.ResourceNotFoundException;
import com.cruiseline.modules.booking.dto.*;
import com.cruiseline.modules.booking.entity.CruiseBooking;
import com.cruiseline.modules.booking.entity.PassengerDetail;
import com.cruiseline.modules.booking.repository.CruiseBookingRepository;
import com.cruiseline.modules.booking.repository.PassengerDetailRepository;
import com.cruiseline.modules.voyage.entity.Cabin;
import com.cruiseline.modules.voyage.entity.CabinCategory;
import com.cruiseline.modules.voyage.entity.Voyage;
import com.cruiseline.modules.voyage.repository.CabinCategoryRepository;
import com.cruiseline.modules.voyage.repository.CabinRepository;
import com.cruiseline.modules.voyage.repository.VoyageRepository;
import com.cruiseline.modules.notification.service.NotificationService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Handles booking lifecycle: creation (with cabin lock + availability
 * decrement), payment recording, and cancellation (with release of cabin).
 * All multi-step mutations run inside a single transaction.
 */
@Service
public class BookingService {

    private final CruiseBookingRepository bookingRepository;
    private final PassengerDetailRepository passengerRepository;
    private final CabinRepository cabinRepository;
    private final CabinCategoryRepository categoryRepository;
    private final VoyageRepository voyageRepository;
    private final AuditService auditService;
    private final NotificationService notificationService;

    @Transactional
    public BookingResponse createBooking(Long leadPassengerId, BookingRequest req) {
        Voyage voyage = voyageRepository.findById(req.getVoyageId())
                .orElseThrow(() -> new ResourceNotFoundException("Voyage", "id", req.getVoyageId()));
        if (voyage.getStatus() != VoyageStatus.OPEN) {
            throw new BusinessRuleException(
                "Bookings are only accepted while the voyage is OPEN; this voyage is " + voyage.getStatus());
        }

        Cabin cabin = cabinRepository.findById(req.getCabinId())
                .orElseThrow(() -> new ResourceNotFoundException("Cabin", "id", req.getCabinId()));

        if (cabin.getStatus() != CabinStatus.AVAILABLE) {
            throw new BusinessRuleException("Cabin " + cabin.getCabinNumber() + " is not available");
        }

        CabinCategory category = cabin.getCategory();
        if (req.getPaxCount() > category.getMaxOccupancy()) {
            throw new BusinessRuleException("Passenger count exceeds cabin max occupancy of " + category.getMaxOccupancy());
        }
        if (category.getAvailableCabins() <= 0) {
            throw new BusinessRuleException("No cabins available in this category");
        }

        BigDecimal totalCost = category.getBasePrice().multiply(BigDecimal.valueOf(req.getPaxCount()));

        CruiseBooking booking = CruiseBooking.builder()
                .leadPassengerId(leadPassengerId)
                .voyageId(req.getVoyageId())
                .cabinId(cabin.getCabinId())
                .paxCount(req.getPaxCount())
                .bookingDate(LocalDate.now())
                .totalCost(totalCost)
                .amountPaid(BigDecimal.ZERO)
                .balanceDue(totalCost)
                .diningPreference(req.getDiningPreference())
                .status(BookingStatus.TENTATIVE)
                .build();
        booking = bookingRepository.save(booking);

        // Lock cabin and decrement availability
        cabin.setStatus(CabinStatus.OCCUPIED);
        category.setAvailableCabins(category.getAvailableCabins() - 1);
        if (category.getAvailableCabins() == 0) {
            category.setStatus(CategoryStatus.SOLD_OUT);
        }

        // Persist passenger details
        final CruiseBooking savedBooking = booking;
        List<PassengerDetail> passengers = req.getPassengers().stream()
                .map(p -> PassengerDetail.builder()
                        .booking(savedBooking)
                        .name(p.getName())
                        .dateOfBirth(p.getDateOfBirth())
                        .gender(p.getGender())
                        .nationality(p.getNationality())
                        .passportNumber(p.getPassportNumber())
                        .passportExpiry(p.getPassportExpiry())
                        .dietaryRestrictions(p.getDietaryRestrictions())
                        .medicalNotes(p.getMedicalNotes())
                        .emergencyContact(p.getEmergencyContact())
                        .status(PassengerStatus.ACTIVE)
                        .build())
                .toList();
        passengerRepository.saveAll(passengers);

        auditService.record("BOOKING_CREATED", "CruiseBooking");
        notificationService.notify(leadPassengerId,
            "Booking #" + booking.getBookingId() + " for " + voyage.getVoyageName()
                + " created. Balance due $" + booking.getBalanceDue() + ".", NotificationCategory.BOOKING);
        return toResponse(booking);
    }

    @Transactional(readOnly = true)
    public BookingResponse getBooking(Long id) {
        return toResponse(findBooking(id));
    }

    @Transactional(readOnly = true)
    public PagedResponse<BookingResponse> myBookings(Long leadPassengerId, Pageable pageable) {
        var page = bookingRepository.findByLeadPassengerId(leadPassengerId, pageable)
                .map(this::toResponse);
        return PagedResponse.from(page);
    }

    @Transactional(readOnly = true)
    public PagedResponse<BookingResponse> bookingsByVoyage(Long voyageId, Pageable pageable) {
        var page = bookingRepository.findByVoyageId(voyageId, pageable).map(this::toResponse);
        return PagedResponse.from(page);
    }

    @Transactional
    public BookingResponse recordPayment(Long id, PaymentRequest req, Long requesterId, boolean privileged) {
        CruiseBooking booking = findBooking(id);
        assertOwner(booking, requesterId, privileged);
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BusinessRuleException("Cannot record payment on a cancelled booking");
        }
        BigDecimal newPaid = booking.getAmountPaid().add(req.getAmount());
        if (newPaid.compareTo(booking.getTotalCost()) > 0) {
            throw new BusinessRuleException("Payment exceeds the outstanding balance");
        }
        booking.setAmountPaid(newPaid);
        booking.setBalanceDue(booking.getTotalCost().subtract(newPaid));
        boolean confirmed = false;
        if (booking.getBalanceDue().compareTo(BigDecimal.ZERO) == 0) {
            booking.setStatus(BookingStatus.CONFIRMED);
            confirmed = true;
        }
        auditService.record("BOOKING_PAYMENT_RECORDED", "CruiseBooking");
        notificationService.notify(booking.getLeadPassengerId(),
            confirmed
                ? "Booking #" + booking.getBookingId() + " is now CONFIRMED. Thank you for your payment."
                : "Payment of $" + req.getAmount() + " received for booking #" + booking.getBookingId()
                    + ". Balance due $" + booking.getBalanceDue() + ".",
            NotificationCategory.BOOKING);
        return toResponse(booking);
    }

    @Transactional
    public BookingResponse cancelBooking(Long id, Long requesterId, boolean privileged) {
        CruiseBooking booking = findBooking(id);
        assertOwner(booking, requesterId, privileged);
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BusinessRuleException("Booking is already cancelled");
        }
        booking.setStatus(BookingStatus.CANCELLED);

        // Release cabin + restore availability
        cabinRepository.findById(booking.getCabinId()).ifPresent(cabin -> {
            cabin.setStatus(CabinStatus.AVAILABLE);
            CabinCategory category = cabin.getCategory();
            category.setAvailableCabins(category.getAvailableCabins() + 1);
            if (category.getStatus() == CategoryStatus.SOLD_OUT) {
                category.setStatus(CategoryStatus.AVAILABLE);
            }
        });

        passengerRepository.findByBookingBookingId(id)
                .forEach(p -> p.setStatus(PassengerStatus.CANCELLED));

        auditService.record("BOOKING_CANCELLED", "CruiseBooking");
        notificationService.notify(booking.getLeadPassengerId(),
            "Booking #" + booking.getBookingId() + " has been cancelled.", NotificationCategory.BOOKING);
        return toResponse(booking);
    }

    @Transactional
    public BookingResponse amendBooking(Long id, AmendBookingRequest req, Long requesterId, boolean privileged) {
        CruiseBooking booking = findBooking(id);
        assertOwner(booking, requesterId, privileged);
        if (booking.getStatus() != BookingStatus.TENTATIVE) {
            throw new BusinessRuleException(
                "Only TENTATIVE bookings can be amended; this booking is " + booking.getStatus());
        }

        Cabin newCabin = cabinRepository.findById(req.getCabinId())
                .orElseThrow(() -> new ResourceNotFoundException("Cabin", "id", req.getCabinId()));
        boolean cabinChanged = !booking.getCabinId().equals(newCabin.getCabinId());
        if (cabinChanged && newCabin.getStatus() != CabinStatus.AVAILABLE) {
            throw new BusinessRuleException("Cabin " + newCabin.getCabinNumber() + " is not available");
        }

        CabinCategory newCategory = newCabin.getCategory();
        if (req.getPaxCount() > newCategory.getMaxOccupancy()) {
            throw new BusinessRuleException(
                "Passenger count exceeds cabin max occupancy of " + newCategory.getMaxOccupancy());
        }

        BigDecimal newTotal = newCategory.getBasePrice().multiply(BigDecimal.valueOf(req.getPaxCount()));
        if (booking.getAmountPaid().compareTo(newTotal) > 0) {
            throw new BusinessRuleException(
                "Amount already paid ($" + booking.getAmountPaid() + ") exceeds the new total ($" + newTotal + ")");
        }

        if (cabinChanged) {
            // Release the old cabin and restore its category availability.
            cabinRepository.findById(booking.getCabinId()).ifPresent(old -> {
                old.setStatus(CabinStatus.AVAILABLE);
                CabinCategory oldCat = old.getCategory();
                oldCat.setAvailableCabins(oldCat.getAvailableCabins() + 1);
                if (oldCat.getStatus() == CategoryStatus.SOLD_OUT) {
                    oldCat.setStatus(CategoryStatus.AVAILABLE);
                }
            });
            // Lock the new cabin and decrement its category availability.
            newCabin.setStatus(CabinStatus.OCCUPIED);
            newCategory.setAvailableCabins(newCategory.getAvailableCabins() - 1);
            if (newCategory.getAvailableCabins() == 0) {
                newCategory.setStatus(CategoryStatus.SOLD_OUT);
            }
            booking.setCabinId(newCabin.getCabinId());
        }

        booking.setPaxCount(req.getPaxCount());
        booking.setDiningPreference(req.getDiningPreference());
        booking.setTotalCost(newTotal);
        booking.setBalanceDue(newTotal.subtract(booking.getAmountPaid()));
        if (booking.getBalanceDue().compareTo(BigDecimal.ZERO) == 0) {
            booking.setStatus(BookingStatus.CONFIRMED);
        }

        auditService.record("BOOKING_AMENDED", "CruiseBooking");
        notificationService.notify(booking.getLeadPassengerId(),
            "Booking #" + booking.getBookingId() + " was updated. New total $" + booking.getTotalCost()
                + ", balance due $" + booking.getBalanceDue() + ".", NotificationCategory.BOOKING);
        return toResponse(booking);
    }

    private void assertOwner(CruiseBooking booking, Long requesterId, boolean privileged) {
        if (!privileged && !booking.getLeadPassengerId().equals(requesterId)) {
            throw new org.springframework.security.access.AccessDeniedException("This booking belongs to another passenger");
        }
    }

    private CruiseBooking findBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CruiseBooking", "id", id));
    }

    private BookingResponse toResponse(CruiseBooking booking) {
        List<PassengerDetailResponse> passengers = passengerRepository
                .findByBookingBookingId(booking.getBookingId()).stream()
                .map(PassengerDetailResponse::from)
                .toList();
        String voyageName = voyageRepository.findById(booking.getVoyageId())
                .map(Voyage::getVoyageName).orElse(null);
        String cabinNumber = cabinRepository.findById(booking.getCabinId())
                .map(Cabin::getCabinNumber).orElse(null);
        return BookingResponse.from(booking, passengers, voyageName, cabinNumber);
    }

    public BookingService(CruiseBookingRepository bookingRepository, PassengerDetailRepository passengerRepository, CabinRepository cabinRepository, CabinCategoryRepository categoryRepository, VoyageRepository voyageRepository, AuditService auditService, NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.cabinRepository = cabinRepository;
        this.categoryRepository = categoryRepository;
        this.voyageRepository = voyageRepository;
        this.auditService = auditService;
        this.notificationService = notificationService;
    }

}
