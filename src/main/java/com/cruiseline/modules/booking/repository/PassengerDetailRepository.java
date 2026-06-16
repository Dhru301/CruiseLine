package com.cruiseline.modules.booking.repository;

import com.cruiseline.modules.booking.entity.PassengerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerDetailRepository extends JpaRepository<PassengerDetail, Long> {
    List<PassengerDetail> findByBookingBookingId(Long bookingId);
}
