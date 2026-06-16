package com.cruiseline.modules.booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Request body for amending an existing (TENTATIVE) booking.
 * cabinId may be the same as the current cabin (no reallocation) or a new
 * AVAILABLE cabin. paxCount and diningPreference are applied as given.
 */
public class AmendBookingRequest {

    @NotNull
    private Long cabinId;

    @NotNull
    @Min(1)
    private Integer paxCount;

    private String diningPreference;

    public Long getCabinId() {
        return this.cabinId;
    }

    public Integer getPaxCount() {
        return this.paxCount;
    }

    public String getDiningPreference() {
        return this.diningPreference;
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

}
