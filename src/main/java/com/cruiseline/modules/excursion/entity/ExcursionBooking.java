package com.cruiseline.modules.excursion.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.ExcursionBookingStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "excursion_bookings", indexes = {
        @Index(name = "idx_exbk_excursion", columnList = "excursion_id"),
        @Index(name = "idx_exbk_pax", columnList = "passenger_id")
})
public class ExcursionBooking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_booking_id")
    private Long exBookingId;

    @Column(name = "excursion_id", nullable = false)
    private Long excursionId;

    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;

    @Column(name = "voyage_id", nullable = false)
    private Long voyageId;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private ExcursionBookingStatus status = ExcursionBookingStatus.BOOKED;

    public ExcursionBooking() {
    }

    public ExcursionBooking(Long exBookingId, Long excursionId, Long passengerId, Long voyageId, LocalDate bookingDate, BigDecimal price, ExcursionBookingStatus status) {
        this.exBookingId = exBookingId;
        this.excursionId = excursionId;
        this.passengerId = passengerId;
        this.voyageId = voyageId;
        this.bookingDate = bookingDate;
        this.price = price;
        this.status = status;
    }

    public Long getExBookingId() {
        return this.exBookingId;
    }

    public Long getExcursionId() {
        return this.excursionId;
    }

    public Long getPassengerId() {
        return this.passengerId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public LocalDate getBookingDate() {
        return this.bookingDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public ExcursionBookingStatus getStatus() {
        return this.status;
    }

    public void setExBookingId(Long exBookingId) {
        this.exBookingId = exBookingId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(ExcursionBookingStatus status) {
        this.status = status;
    }

    public static ExcursionBookingBuilder builder() {
        return new ExcursionBookingBuilder();
    }

    public static class ExcursionBookingBuilder {
        private Long exBookingId;
        private Long excursionId;
        private Long passengerId;
        private Long voyageId;
        private LocalDate bookingDate;
        private BigDecimal price;
        private ExcursionBookingStatus status = ExcursionBookingStatus.BOOKED;

        public ExcursionBookingBuilder exBookingId(Long exBookingId) {
            this.exBookingId = exBookingId;
            return this;
        }
        public ExcursionBookingBuilder excursionId(Long excursionId) {
            this.excursionId = excursionId;
            return this;
        }
        public ExcursionBookingBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public ExcursionBookingBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public ExcursionBookingBuilder bookingDate(LocalDate bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }
        public ExcursionBookingBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }
        public ExcursionBookingBuilder status(ExcursionBookingStatus status) {
            this.status = status;
            return this;
        }

        public ExcursionBooking build() {
            ExcursionBooking instance = new ExcursionBooking();
            instance.exBookingId = this.exBookingId;
            instance.excursionId = this.excursionId;
            instance.passengerId = this.passengerId;
            instance.voyageId = this.voyageId;
            instance.bookingDate = this.bookingDate;
            instance.price = this.price;
            instance.status = this.status;
            return instance;
        }
    }

}
