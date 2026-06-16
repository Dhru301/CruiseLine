package com.cruiseline.modules.booking.dto;

import com.cruiseline.common.enums.BookingStatus;
import com.cruiseline.modules.booking.entity.CruiseBooking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookingResponse {
    private Long bookingId;
    private Long leadPassengerId;
    private Long voyageId;
    private String voyageName;
    private Long cabinId;
    private String cabinNumber;
    private Integer paxCount;
    private LocalDate bookingDate;
    private BigDecimal totalCost;
    private BigDecimal amountPaid;
    private BigDecimal balanceDue;
    private String diningPreference;
    private BookingStatus status;
    private List<PassengerDetailResponse> passengers;

    public static BookingResponse from(CruiseBooking b, List<PassengerDetailResponse> passengers) {
        return from(b, passengers, null, null);
    }

    public static BookingResponse from(CruiseBooking b, List<PassengerDetailResponse> passengers,
                                       String voyageName, String cabinNumber) {
        return BookingResponse.builder()
                .bookingId(b.getBookingId())
                .leadPassengerId(b.getLeadPassengerId())
                .voyageId(b.getVoyageId())
                .voyageName(voyageName)
                .cabinId(b.getCabinId())
                .cabinNumber(cabinNumber)
                .paxCount(b.getPaxCount())
                .bookingDate(b.getBookingDate())
                .totalCost(b.getTotalCost())
                .amountPaid(b.getAmountPaid())
                .balanceDue(b.getBalanceDue())
                .diningPreference(b.getDiningPreference())
                .status(b.getStatus())
                .passengers(passengers)
                .build();
    }

    public BookingResponse() {
    }

    public BookingResponse(Long bookingId, Long leadPassengerId, Long voyageId, Long cabinId, Integer paxCount, LocalDate bookingDate, BigDecimal totalCost, BigDecimal amountPaid, BigDecimal balanceDue, String diningPreference, BookingStatus status, List<PassengerDetailResponse> passengers) {
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
        this.passengers = passengers;
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

    public String getVoyageName() {
        return this.voyageName;
    }

    public Long getCabinId() {
        return this.cabinId;
    }

    public String getCabinNumber() {
        return this.cabinNumber;
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

    public List<PassengerDetailResponse> getPassengers() {
        return this.passengers;
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

    public void setVoyageName(String voyageName) {
        this.voyageName = voyageName;
    }

    public void setCabinId(Long cabinId) {
        this.cabinId = cabinId;
    }

    public void setCabinNumber(String cabinNumber) {
        this.cabinNumber = cabinNumber;
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

    public void setPassengers(List<PassengerDetailResponse> passengers) {
        this.passengers = passengers;
    }

    public static BookingResponseBuilder builder() {
        return new BookingResponseBuilder();
    }

    public static class BookingResponseBuilder {
        private Long bookingId;
        private Long leadPassengerId;
        private Long voyageId;
        private String voyageName;
        private Long cabinId;
        private String cabinNumber;
        private Integer paxCount;
        private LocalDate bookingDate;
        private BigDecimal totalCost;
        private BigDecimal amountPaid;
        private BigDecimal balanceDue;
        private String diningPreference;
        private BookingStatus status;
        private List<PassengerDetailResponse> passengers;

        public BookingResponseBuilder bookingId(Long bookingId) {
            this.bookingId = bookingId;
            return this;
        }
        public BookingResponseBuilder leadPassengerId(Long leadPassengerId) {
            this.leadPassengerId = leadPassengerId;
            return this;
        }
        public BookingResponseBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public BookingResponseBuilder voyageName(String voyageName) {
            this.voyageName = voyageName;
            return this;
        }
        public BookingResponseBuilder cabinId(Long cabinId) {
            this.cabinId = cabinId;
            return this;
        }
        public BookingResponseBuilder cabinNumber(String cabinNumber) {
            this.cabinNumber = cabinNumber;
            return this;
        }
        public BookingResponseBuilder paxCount(Integer paxCount) {
            this.paxCount = paxCount;
            return this;
        }
        public BookingResponseBuilder bookingDate(LocalDate bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }
        public BookingResponseBuilder totalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }
        public BookingResponseBuilder amountPaid(BigDecimal amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }
        public BookingResponseBuilder balanceDue(BigDecimal balanceDue) {
            this.balanceDue = balanceDue;
            return this;
        }
        public BookingResponseBuilder diningPreference(String diningPreference) {
            this.diningPreference = diningPreference;
            return this;
        }
        public BookingResponseBuilder status(BookingStatus status) {
            this.status = status;
            return this;
        }
        public BookingResponseBuilder passengers(List<PassengerDetailResponse> passengers) {
            this.passengers = passengers;
            return this;
        }

        public BookingResponse build() {
            BookingResponse instance = new BookingResponse();
            instance.bookingId = this.bookingId;
            instance.leadPassengerId = this.leadPassengerId;
            instance.voyageId = this.voyageId;
            instance.voyageName = this.voyageName;
            instance.cabinId = this.cabinId;
            instance.cabinNumber = this.cabinNumber;
            instance.paxCount = this.paxCount;
            instance.bookingDate = this.bookingDate;
            instance.totalCost = this.totalCost;
            instance.amountPaid = this.amountPaid;
            instance.balanceDue = this.balanceDue;
            instance.diningPreference = this.diningPreference;
            instance.status = this.status;
            instance.passengers = this.passengers;
            return instance;
        }
    }

}
