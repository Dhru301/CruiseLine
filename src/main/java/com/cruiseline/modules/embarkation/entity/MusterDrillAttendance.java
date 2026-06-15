package com.cruiseline.modules.embarkation.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.AttendanceStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "muster_drill_attendance", indexes = @Index(name = "idx_drill_voyage", columnList = "voyage_id"))
public class MusterDrillAttendance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drill_id")
    private Long drillId;

    @Column(name = "muster_id", nullable = false)
    private Long musterId;

    @Column(name = "voyage_id", nullable = false)
    private Long voyageId;

    @Column(nullable = false)
    private LocalDate drillDate;

    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AttendanceStatus attendanceStatus;

    @Column(name = "recorded_by_id")
    private Long recordedById;

    public MusterDrillAttendance() {
    }

    public MusterDrillAttendance(Long drillId, Long musterId, Long voyageId, LocalDate drillDate, Long passengerId, AttendanceStatus attendanceStatus, Long recordedById) {
        this.drillId = drillId;
        this.musterId = musterId;
        this.voyageId = voyageId;
        this.drillDate = drillDate;
        this.passengerId = passengerId;
        this.attendanceStatus = attendanceStatus;
        this.recordedById = recordedById;
    }

    public Long getDrillId() {
        return this.drillId;
    }

    public Long getMusterId() {
        return this.musterId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public LocalDate getDrillDate() {
        return this.drillDate;
    }

    public Long getPassengerId() {
        return this.passengerId;
    }

    public AttendanceStatus getAttendanceStatus() {
        return this.attendanceStatus;
    }

    public Long getRecordedById() {
        return this.recordedById;
    }

    public void setDrillId(Long drillId) {
        this.drillId = drillId;
    }

    public void setMusterId(Long musterId) {
        this.musterId = musterId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setDrillDate(LocalDate drillDate) {
        this.drillDate = drillDate;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public void setRecordedById(Long recordedById) {
        this.recordedById = recordedById;
    }

    public static MusterDrillAttendanceBuilder builder() {
        return new MusterDrillAttendanceBuilder();
    }

    public static class MusterDrillAttendanceBuilder {
        private Long drillId;
        private Long musterId;
        private Long voyageId;
        private LocalDate drillDate;
        private Long passengerId;
        private AttendanceStatus attendanceStatus;
        private Long recordedById;

        public MusterDrillAttendanceBuilder drillId(Long drillId) {
            this.drillId = drillId;
            return this;
        }
        public MusterDrillAttendanceBuilder musterId(Long musterId) {
            this.musterId = musterId;
            return this;
        }
        public MusterDrillAttendanceBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public MusterDrillAttendanceBuilder drillDate(LocalDate drillDate) {
            this.drillDate = drillDate;
            return this;
        }
        public MusterDrillAttendanceBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public MusterDrillAttendanceBuilder attendanceStatus(AttendanceStatus attendanceStatus) {
            this.attendanceStatus = attendanceStatus;
            return this;
        }
        public MusterDrillAttendanceBuilder recordedById(Long recordedById) {
            this.recordedById = recordedById;
            return this;
        }

        public MusterDrillAttendance build() {
            MusterDrillAttendance instance = new MusterDrillAttendance();
            instance.drillId = this.drillId;
            instance.musterId = this.musterId;
            instance.voyageId = this.voyageId;
            instance.drillDate = this.drillDate;
            instance.passengerId = this.passengerId;
            instance.attendanceStatus = this.attendanceStatus;
            instance.recordedById = this.recordedById;
            return instance;
        }
    }

}
