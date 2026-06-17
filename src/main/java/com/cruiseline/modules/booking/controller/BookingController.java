package com.cruiseline.modules.booking.controller;

import com.cruiseline.common.dto.ApiResponse;
import com.cruiseline.common.dto.PagedResponse;
import com.cruiseline.config.UserPrincipal;
import com.cruiseline.modules.booking.dto.AmendBookingRequest;
import com.cruiseline.modules.booking.dto.BookingRequest;
import com.cruiseline.modules.booking.dto.BookingResponse;
import com.cruiseline.modules.booking.dto.PaymentRequest;
import com.cruiseline.modules.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Passenger Booking", description = "Cruise bookings, payments and cancellations")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Create a booking (PASSENGER). Lead passenger is the current user.")
    @PostMapping
    @PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
    public ResponseEntity<ApiResponse<BookingResponse>> create(@AuthenticationPrincipal UserPrincipal principal,
                                                              @Valid @RequestBody BookingRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Booking created", bookingService.createBooking(principal.getUserId(), req)));
    }

    @Operation(summary = "Get a booking by id")
    @GetMapping("/{id}")
    public ApiResponse<BookingResponse> get(@PathVariable Long id) {
        return ApiResponse.ok(bookingService.getBooking(id));
    }

    @Operation(summary = "List the current user's bookings")
    @GetMapping("/me")
    public ApiResponse<PagedResponse<BookingResponse>> myBookings(@AuthenticationPrincipal UserPrincipal principal,
                                                                 Pageable pageable) {
        return ApiResponse.ok(bookingService.myBookings(principal.getUserId(), pageable));
    }

    @Operation(summary = "List bookings for a voyage (staff)")
    @GetMapping("/voyage/{voyageId}")
    @PreAuthorize("hasAnyRole('ADMIN','PURSER','EMBARKATION_OFFICER','ONBOARD_AGENT')")
    public ApiResponse<PagedResponse<BookingResponse>> byVoyage(@PathVariable Long voyageId, Pageable pageable) {
        return ApiResponse.ok(bookingService.bookingsByVoyage(voyageId, pageable));
    }

    @Operation(summary = "Record a payment against a booking")
    @PostMapping("/{id}/payments")
    @PreAuthorize("hasAnyRole('PASSENGER','PURSER','ADMIN')")
    public ApiResponse<BookingResponse> pay(@AuthenticationPrincipal UserPrincipal principal,
                                            @PathVariable Long id, @Valid @RequestBody PaymentRequest req) {
        boolean privileged = principal.getRole().equals("ADMIN") || principal.getRole().equals("PURSER");
        return ApiResponse.ok("Payment recorded", bookingService.recordPayment(id, req, principal.getUserId(), privileged));
    }

    @Operation(summary = "Cancel a booking")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
    public ApiResponse<BookingResponse> cancel(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long id) {
        boolean privileged = principal.getRole().equals("ADMIN");
        return ApiResponse.ok("Booking cancelled", bookingService.cancelBooking(id, principal.getUserId(), privileged));
    }

    @Operation(summary = "Amend a TENTATIVE booking (change cabin, pax count, or dining)")
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
    public ApiResponse<BookingResponse> amend(@AuthenticationPrincipal UserPrincipal principal,
                                              @PathVariable Long id, @Valid @RequestBody AmendBookingRequest req) {
        boolean privileged = principal.getRole().equals("ADMIN");
        return ApiResponse.ok("Booking updated", bookingService.amendBooking(id, req, principal.getUserId(), privileged));
    }

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

}
