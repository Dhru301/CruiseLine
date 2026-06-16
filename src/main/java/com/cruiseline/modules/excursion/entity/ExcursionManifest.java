package com.cruiseline.modules.excursion.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.ManifestStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "excursion_manifests")
public class ExcursionManifest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manifest_id")
    private Long manifestId;

    @Column(name = "excursion_id", nullable = false)
    private Long excursionId;

    @Column(name = "voyage_id", nullable = false)
    private Long voyageId;

    private LocalDate portDate;

    private Integer totalBooked;

    @Column(length = 150)
    private String meetingPoint;

    private LocalTime departureTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private ManifestStatus status = ManifestStatus.DRAFT;

    public ExcursionManifest() {
    }

    public ExcursionManifest(Long manifestId, Long excursionId, Long voyageId, LocalDate portDate, Integer totalBooked, String meetingPoint, LocalTime departureTime, ManifestStatus status) {
        this.manifestId = manifestId;
        this.excursionId = excursionId;
        this.voyageId = voyageId;
        this.portDate = portDate;
        this.totalBooked = totalBooked;
        this.meetingPoint = meetingPoint;
        this.departureTime = departureTime;
        this.status = status;
    }

    public Long getManifestId() {
        return this.manifestId;
    }

    public Long getExcursionId() {
        return this.excursionId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public LocalDate getPortDate() {
        return this.portDate;
    }

    public Integer getTotalBooked() {
        return this.totalBooked;
    }

    public String getMeetingPoint() {
        return this.meetingPoint;
    }

    public LocalTime getDepartureTime() {
        return this.departureTime;
    }

    public ManifestStatus getStatus() {
        return this.status;
    }

    public void setManifestId(Long manifestId) {
        this.manifestId = manifestId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setPortDate(LocalDate portDate) {
        this.portDate = portDate;
    }

    public void setTotalBooked(Integer totalBooked) {
        this.totalBooked = totalBooked;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setStatus(ManifestStatus status) {
        this.status = status;
    }

    public static ExcursionManifestBuilder builder() {
        return new ExcursionManifestBuilder();
    }

    public static class ExcursionManifestBuilder {
        private Long manifestId;
        private Long excursionId;
        private Long voyageId;
        private LocalDate portDate;
        private Integer totalBooked;
        private String meetingPoint;
        private LocalTime departureTime;
        private ManifestStatus status = ManifestStatus.DRAFT;

        public ExcursionManifestBuilder manifestId(Long manifestId) {
            this.manifestId = manifestId;
            return this;
        }
        public ExcursionManifestBuilder excursionId(Long excursionId) {
            this.excursionId = excursionId;
            return this;
        }
        public ExcursionManifestBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public ExcursionManifestBuilder portDate(LocalDate portDate) {
            this.portDate = portDate;
            return this;
        }
        public ExcursionManifestBuilder totalBooked(Integer totalBooked) {
            this.totalBooked = totalBooked;
            return this;
        }
        public ExcursionManifestBuilder meetingPoint(String meetingPoint) {
            this.meetingPoint = meetingPoint;
            return this;
        }
        public ExcursionManifestBuilder departureTime(LocalTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }
        public ExcursionManifestBuilder status(ManifestStatus status) {
            this.status = status;
            return this;
        }

        public ExcursionManifest build() {
            ExcursionManifest instance = new ExcursionManifest();
            instance.manifestId = this.manifestId;
            instance.excursionId = this.excursionId;
            instance.voyageId = this.voyageId;
            instance.portDate = this.portDate;
            instance.totalBooked = this.totalBooked;
            instance.meetingPoint = this.meetingPoint;
            instance.departureTime = this.departureTime;
            instance.status = this.status;
            return instance;
        }
    }

}
