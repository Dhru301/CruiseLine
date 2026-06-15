package com.cruiseline.modules.booking.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.BookingStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cruise_bookings", indexes = {
        @Index(name = "idx_booking_voyage", columnList = "voyage_id"),
        @Index(name = "idx_booking_lead", columnList = "lead_passenger_id")
})
public class CruiseBooking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "lead_passenger_id", nullable = false)
    private Long leadPassengerId;   // FK to users.user_id

    @Column(name = "voyage_id", nullable = false)
    private Long voyageId;

    @Column(name = "cabin_id", nullable = false)
    private Long cabinId;

    @Column(nullable = false)
    private Integer paxCount;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalCost;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amountPaid = BigDecimal.ZERO;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal balanceDue;

    @Column(length = 60)
    private String diningPreference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private BookingStatus status = BookingStatus.TENTATIVE;

    public CruiseBooking() {
    }

    public CruiseBooking(Long bookingId, Long leadPassengerId, Long voyageId, Long cabinId, Integer paxCount, LocalDate bookingDate, BigDecimal totalCost, BigDecimal amountPaid, BigDecimal balanceDue, String diningPreference, BookingStatus status) {
        this.bookingId = bookingId;
        this.leadPassengerId = leadPassengerId;
        this.voyageId = voyageId;
        this.cabinId = cabinId;
        this.paxCount = paxCount;
        this.bookingDate = bookingDate;
        this.totalCost = totalCost;
        this.amountPaid = amountPaid;
        this.balanceDue = balanceDue;
        this.diningPreference = diningPreference;
        this.status = status;
    }

    public Long getBookingId() {
        return this.bookingId;
    }

    public Long getLeadPassengerId() {
        return this.leadPassengerId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public Long getCabinId() {
        return this.cabinId;
    }

    public Integer getPaxCount() {
        return this.paxCount;
    }

    public LocalDate getBookingDate() {
        return this.bookingDate;
    }

    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public BigDecimal getAmountPaid() {
        return this.amountPaid;
    }

    public BigDecimal getBalanceDue() {
        return this.balanceDue;
    }

    public String getDiningPreference() {
        return this.diningPreference;
    }

    public BookingStatus getStatus() {
        return this.status;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setLeadPassengerId(Long leadPassengerId) {
        this.leadPassengerId = leadPassengerId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setCabinId(Long cabinId) {
        this.cabinId = cabinId;
    }

    public void setPaxCount(Integer paxCount) {
        this.paxCount = paxCount;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setBalanceDue(BigDecimal balanceDue) {
        this.balanceDue = balanceDue;
    }

    public void setDiningPreference(String diningPreference) {
        this.diningPreference = diningPreference;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public static CruiseBookingBuilder builder() {
        return new CruiseBookingBuilder();
    }

    public static class CruiseBookingBuilder {
        private Long bookingId;
        private Long leadPassengerId;
        private Long voyageId;
        private Long cabinId;
        private Integer paxCount;
        private LocalDate bookingDate;
        private BigDecimal totalCost;
        private BigDecimal amountPaid = BigDecimal.ZERO;
        private BigDecimal balanceDue;
        private String diningPreference;
        private BookingStatus status = BookingStatus.TENTATIVE;

        public CruiseBookingBuilder bookingId(Long bookingId) {
            this.bookingId = bookingId;
            return this;
        }
        public CruiseBookingBuilder leadPassengerId(Long leadPassengerId) {
            this.leadPassengerId = leadPassengerId;
            return this;
        }
        public CruiseBookingBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public CruiseBookingBuilder cabinId(Long cabinId) {
            this.cabinId = cabinId;
            return this;
        }
        public CruiseBookingBuilder paxCount(Integer paxCount) {
            this.paxCount = paxCount;
            return this;
        }
        public CruiseBookingBuilder bookingDate(LocalDate bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }
        public CruiseBookingBuilder totalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }
        public CruiseBookingBuilder amountPaid(BigDecimal amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }
        public CruiseBookingBuilder balanceDue(BigDecimal balanceDue) {
            this.balanceDue = balanceDue;
            return this;
        }
        public CruiseBookingBuilder diningPreference(String diningPreference) {
            this.diningPreference = diningPreference;
            return this;
        }
        public CruiseBookingBuilder status(BookingStatus status) {
            this.status = status;
            return this;
        }

        public CruiseBooking build() {
            CruiseBooking instance = new CruiseBooking();
            instance.bookingId = this.bookingId;
            instance.leadPassengerId = this.leadPassengerId;
            instance.voyageId = this.voyageId;
            instance.cabinId = this.cabinId;
            instance.paxCount = this.paxCount;
            instance.bookingDate = this.bookingDate;
            instance.totalCost = this.totalCost;
            instance.amountPaid = this.amountPaid;
            instance.balanceDue = this.balanceDue;
            instance.diningPreference = this.diningPreference;
            instance.status = this.status;
            return instance;
        }
    }

}
