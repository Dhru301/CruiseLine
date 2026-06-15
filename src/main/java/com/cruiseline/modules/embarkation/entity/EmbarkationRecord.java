package com.cruiseline.modules.embarkation.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.EmbarkationStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "embarkation_records", indexes = {
        @Index(name = "idx_embark_voyage", columnList = "voyage_id"),
        @Index(name = "idx_embark_pax", columnList = "passenger_id")
})
public class EmbarkationRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "embark_record_id")
    private Long embarkRecordId;

    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;

    @Column(name = "voyage_id", nullable = false)
    private Long voyageId;

    private Instant checkInDateTime;

    private Boolean documentVerified = false;

    private Boolean boardingPassIssued = false;

    @Column(name = "muster_station_id")
    private Long musterStationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EmbarkationStatus status = EmbarkationStatus.CHECKED_IN;

    public EmbarkationRecord() {
    }

    public EmbarkationRecord(Long embarkRecordId, Long passengerId, Long voyageId, Instant checkInDateTime, Boolean documentVerified, Boolean boardingPassIssued, Long musterStationId, EmbarkationStatus status) {
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

    public static EmbarkationRecordBuilder builder() {
        return new EmbarkationRecordBuilder();
    }

    public static class EmbarkationRecordBuilder {
        private Long embarkRecordId;
        private Long passengerId;
        private Long voyageId;
        private Instant checkInDateTime;
        private Boolean documentVerified = false;
        private Boolean boardingPassIssued = false;
        private Long musterStationId;
        private EmbarkationStatus status = EmbarkationStatus.CHECKED_IN;

        public EmbarkationRecordBuilder embarkRecordId(Long embarkRecordId) {
            this.embarkRecordId = embarkRecordId;
            return this;
        }
        public EmbarkationRecordBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public EmbarkationRecordBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public EmbarkationRecordBuilder checkInDateTime(Instant checkInDateTime) {
            this.checkInDateTime = checkInDateTime;
            return this;
        }
        public EmbarkationRecordBuilder documentVerified(Boolean documentVerified) {
            this.documentVerified = documentVerified;
            return this;
        }
        public EmbarkationRecordBuilder boardingPassIssued(Boolean boardingPassIssued) {
            this.boardingPassIssued = boardingPassIssued;
            return this;
        }
        public EmbarkationRecordBuilder musterStationId(Long musterStationId) {
            this.musterStationId = musterStationId;
            return this;
        }
        public EmbarkationRecordBuilder status(EmbarkationStatus status) {
            this.status = status;
            return this;
        }

        public EmbarkationRecord build() {
            EmbarkationRecord instance = new EmbarkationRecord();
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
