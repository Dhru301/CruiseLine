package com.cts.CruiseLine.dto;

import com.cts.CruiseLine.enums.EmbarkationStatus;
import com.cts.CruiseLine.entity.EmbarkationRecord;

import java.time.Instant;

public class EmbarkationResponse {
    private Long embarkRecordId;
    private Long passengerId;
    private Long voyageId;
    private Instant checkInDateTime;
    private Boolean documentVerified;
    private Boolean boardingPassIssued;
    private Long musterStationId;
    private EmbarkationStatus status;

    public static EmbarkationResponse from(EmbarkationRecord r) {
        return EmbarkationResponse.builder()
                .embarkRecordId(r.getEmbarkRecordId())
                .passengerId(r.getPassengerId())
                .voyageId(r.getVoyageId())
                .checkInDateTime(r.getCheckInDateTime())
                .documentVerified(r.getDocumentVerified())
                .boardingPassIssued(r.getBoardingPassIssued())
                .musterStationId(r.getMusterStationId())
                .status(r.getStatus())
                .build();
    }

    public EmbarkationResponse() {
    }

    public EmbarkationResponse(Long embarkRecordId, Long passengerId, Long voyageId, Instant checkInDateTime, Boolean documentVerified, Boolean boardingPassIssued, Long musterStationId, EmbarkationStatus status) {
        this.embarkRecordId = embarkRecordId;
        this.passengerId = passengerId;
        this.voyageId = voyageId;
        this.checkInDateTime = checkInDateTime;
        this.documentVerified = documentVerified;
        this.boardingPassIssued = boardingPassIssued;
        this.musterStationId = musterStationId;
        this.status = status;
    }

    public Long getEmbarkRecordId() {
        return this.embarkRecordId;
    }

    public Long getPassengerId() {
        return this.passengerId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public Instant getCheckInDateTime() {
        return this.checkInDateTime;
    }

    public Boolean getDocumentVerified() {
        return this.documentVerified;
    }

    public Boolean getBoardingPassIssued() {
        return this.boardingPassIssued;
    }

    public Long getMusterStationId() {
        return this.musterStationId;
    }

    public EmbarkationStatus getStatus() {
        return this.status;
    }

    public void setEmbarkRecordId(Long embarkRecordId) {
        this.embarkRecordId = embarkRecordId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setCheckInDateTime(Instant checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public void setDocumentVerified(Boolean documentVerified) {
        this.documentVerified = documentVerified;
    }

    public void setBoardingPassIssued(Boolean boardingPassIssued) {
        this.boardingPassIssued = boardingPassIssued;
    }

    public void setMusterStationId(Long musterStationId) {
        this.musterStationId = musterStationId;
    }

    public void setStatus(EmbarkationStatus status) {
        this.status = status;
    }

    public static EmbarkationResponseBuilder builder() {
        return new EmbarkationResponseBuilder();
    }

    public static class EmbarkationResponseBuilder {
        private Long embarkRecordId;
        private Long passengerId;
        private Long voyageId;
        private Instant checkInDateTime;
        private Boolean documentVerified;
        private Boolean boardingPassIssued;
        private Long musterStationId;
        private EmbarkationStatus status;

        public EmbarkationResponseBuilder embarkRecordId(Long embarkRecordId) {
            this.embarkRecordId = embarkRecordId;
            return this;
        }
        public EmbarkationResponseBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public EmbarkationResponseBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public EmbarkationResponseBuilder checkInDateTime(Instant checkInDateTime) {
            this.checkInDateTime = checkInDateTime;
            return this;
        }
        public EmbarkationResponseBuilder documentVerified(Boolean documentVerified) {
            this.documentVerified = documentVerified;
            return this;
        }
        public EmbarkationResponseBuilder boardingPassIssued(Boolean boardingPassIssued) {
            this.boardingPassIssued = boardingPassIssued;
            return this;
        }
        public EmbarkationResponseBuilder musterStationId(Long musterStationId) {
            this.musterStationId = musterStationId;
            return this;
        }
        public EmbarkationResponseBuilder status(EmbarkationStatus status) {
            this.status = status;
            return this;
        }

        public EmbarkationResponse build() {
            EmbarkationResponse instance = new EmbarkationResponse();
            instance.embarkRecordId = this.embarkRecordId;
            instance.passengerId = this.passengerId;
            instance.voyageId = this.voyageId;
            instance.checkInDateTime = this.checkInDateTime;
            instance.documentVerified = this.documentVerified;
            instance.boardingPassIssued = this.boardingPassIssued;
            instance.musterStationId = this.musterStationId;
            instance.status = this.status;
            return instance;
        }
    }

}
