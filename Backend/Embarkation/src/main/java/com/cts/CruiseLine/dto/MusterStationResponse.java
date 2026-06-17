package com.cts.CruiseLine.dto;

import com.cts.CruiseLine.enums.MusterStationStatus;
import com.cts.CruiseLine.entity.MusterStation;

public class MusterStationResponse {
    private Long musterId;
    private Long voyageId;
    private String stationCode;
    private String deck;
    private String assignedCabinRange;
    private Integer capacity;
    private MusterStationStatus status;

    public static MusterStationResponse from(MusterStation m) {
        return MusterStationResponse.builder()
                .musterId(m.getMusterId())
                .voyageId(m.getVoyageId())
                .stationCode(m.getStationCode())
                .deck(m.getDeck())
                .assignedCabinRange(m.getAssignedCabinRange())
                .capacity(m.getCapacity())
                .status(m.getStatus())
                .build();
    }

    public MusterStationResponse() {
    }

    public MusterStationResponse(Long musterId, Long voyageId, String stationCode, String deck, String assignedCabinRange, Integer capacity, MusterStationStatus status) {
        this.musterId = musterId;
        this.voyageId = voyageId;
        this.stationCode = stationCode;
        this.deck = deck;
        this.assignedCabinRange = assignedCabinRange;
        this.capacity = capacity;
        this.status = status;
    }

    public Long getMusterId() {
        return this.musterId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public String getStationCode() {
        return this.stationCode;
    }

    public String getDeck() {
        return this.deck;
    }

    public String getAssignedCabinRange() {
        return this.assignedCabinRange;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public MusterStationStatus getStatus() {
        return this.status;
    }

    public void setMusterId(Long musterId) {
        this.musterId = musterId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public void setAssignedCabinRange(String assignedCabinRange) {
        this.assignedCabinRange = assignedCabinRange;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setStatus(MusterStationStatus status) {
        this.status = status;
    }

    public static MusterStationResponseBuilder builder() {
        return new MusterStationResponseBuilder();
    }

    public static class MusterStationResponseBuilder {
        private Long musterId;
        private Long voyageId;
        private String stationCode;
        private String deck;
        private String assignedCabinRange;
        private Integer capacity;
        private MusterStationStatus status;

        public MusterStationResponseBuilder musterId(Long musterId) {
            this.musterId = musterId;
            return this;
        }
        public MusterStationResponseBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public MusterStationResponseBuilder stationCode(String stationCode) {
            this.stationCode = stationCode;
            return this;
        }
        public MusterStationResponseBuilder deck(String deck) {
            this.deck = deck;
            return this;
        }
        public MusterStationResponseBuilder assignedCabinRange(String assignedCabinRange) {
            this.assignedCabinRange = assignedCabinRange;
            return this;
        }
        public MusterStationResponseBuilder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }
        public MusterStationResponseBuilder status(MusterStationStatus status) {
            this.status = status;
            return this;
        }

        public MusterStationResponse build() {
            MusterStationResponse instance = new MusterStationResponse();
            instance.musterId = this.musterId;
            instance.voyageId = this.voyageId;
            instance.stationCode = this.stationCode;
            instance.deck = this.deck;
            instance.assignedCabinRange = this.assignedCabinRange;
            instance.capacity = this.capacity;
            instance.status = this.status;
            return instance;
        }
    }

}
