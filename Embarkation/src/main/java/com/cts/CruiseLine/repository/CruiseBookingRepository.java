package com.cts.CruiseLine.repository;

import com.cts.CruiseLine.enums.BookingStatus;
import org.springframework.stereotype.Repository;

@Repository
public class CruiseBookingRepository {

    public boolean existsByLeadPassengerIdAndVoyageIdAndStatusNot(
            Long passengerId, Long voyageId, BookingStatus excludedStatus) {
        return true;
    }

    public boolean existsByLeadPassengerIdAndVoyageIdAndStatus(
            Long passengerId, Long voyageId, BookingStatus status) {
        return true;
    }
}