package com.cruiseline.modules.booking.repository;

import com.cruiseline.common.enums.BookingStatus;
import com.cruiseline.modules.booking.entity.CruiseBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CruiseBookingRepository extends JpaRepository<CruiseBooking, Long> {
    Page<CruiseBooking> findByLeadPassengerId(Long leadPassengerId, Pageable pageable);
    Page<CruiseBooking> findByVoyageId(Long voyageId, Pageable pageable);
    List<CruiseBooking> findByVoyageIdAndStatus(Long voyageId, BookingStatus status);
    List<CruiseBooking> findByVoyageId(Long voyageId);
    boolean existsByLeadPassengerIdAndVoyageIdAndStatusNot(Long leadPassengerId, Long voyageId, BookingStatus status);
    boolean existsByLeadPassengerIdAndVoyageIdAndStatus(Long leadPassengerId, Long voyageId, BookingStatus status);
}
