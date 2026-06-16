package com.cruiseline.modules.booking.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class BookingRequest {
    @NotNull
    private Long voyageId;

    @NotNull
    private Long cabinId;

    @NotNull @Min(1)
    private Integer paxCount;

    private String diningPreference;

    @NotEmpty
    @Valid
    private List<PassengerDetailRequest> passengers;

    public Long getVoyageId() {
        return this.voyageId;
    }

    public Long getCabinId() {
        return this.cabinId;
    }

    public Integer getPaxCount() {
        return this.paxCount;
    }

    public String getDiningPreference() {
        return this.diningPreference;
    }

    public List<PassengerDetailRequest> getPassengers() {
        return this.passengers;
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

    public void setDiningPreference(String diningPreference) {
        this.diningPreference = diningPreference;
    }

    public void setPassengers(List<PassengerDetailRequest> passengers) {
        this.passengers = passengers;
    }

}
