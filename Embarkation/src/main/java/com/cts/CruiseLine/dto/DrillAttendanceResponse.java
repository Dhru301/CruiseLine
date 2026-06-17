package com.cts.CruiseLine.dto;

import com.cts.CruiseLine.enums.AttendanceStatus;
import com.cts.CruiseLine.entity.MusterDrillAttendance;

import java.time.LocalDate;

public class DrillAttendanceResponse {
    private Long drillId;
    private Long musterId;
    private Long voyageId;
    private Long passengerId;
    private LocalDate drillDate;
    private AttendanceStatus attendanceStatus;
    private Long recordedById;

    public static DrillAttendanceResponse from(MusterDrillAttendance d) {
        return DrillAttendanceResponse.builder()
                .drillId(d.getDrillId())
                .musterId(d.getMusterId())
                .voyageId(d.getVoyageId())
                .passengerId(d.getPassengerId())
                .drillDate(d.getDrillDate())
                .attendanceStatus(d.getAttendanceStatus())
                .recordedById(d.getRecordedById())
                .build();
    }

    public DrillAttendanceResponse() {
    }

    public DrillAttendanceResponse(Long drillId, Long musterId, Long voyageId, Long passengerId, LocalDate drillDate, AttendanceStatus attendanceStatus, Long recordedById) {
        this.drillId = drillId;
        this.musterId = musterId;
        this.voyageId = voyageId;
        this.passengerId = passengerId;
        this.drillDate = drillDate;
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

    public Long getPassengerId() {
        return this.passengerId;
    }

    public LocalDate getDrillDate() {
        return this.drillDate;
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

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setDrillDate(LocalDate drillDate) {
        this.drillDate = drillDate;
    }

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public void setRecordedById(Long recordedById) {
        this.recordedById = recordedById;
    }

    public static DrillAttendanceResponseBuilder builder() {
        return new DrillAttendanceResponseBuilder();
    }

    public static class DrillAttendanceResponseBuilder {
        private Long drillId;
        private Long musterId;
        private Long voyageId;
        private Long passengerId;
        private LocalDate drillDate;
        private AttendanceStatus attendanceStatus;
        private Long recordedById;

        public DrillAttendanceResponseBuilder drillId(Long drillId) {
            this.drillId = drillId;
            return this;
        }
        public DrillAttendanceResponseBuilder musterId(Long musterId) {
            this.musterId = musterId;
            return this;
        }
        public DrillAttendanceResponseBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public DrillAttendanceResponseBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public DrillAttendanceResponseBuilder drillDate(LocalDate drillDate) {
            this.drillDate = drillDate;
            return this;
        }
        public DrillAttendanceResponseBuilder attendanceStatus(AttendanceStatus attendanceStatus) {
            this.attendanceStatus = attendanceStatus;
            return this;
        }
        public DrillAttendanceResponseBuilder recordedById(Long recordedById) {
            this.recordedById = recordedById;
            return this;
        }

        public DrillAttendanceResponse build() {
            DrillAttendanceResponse instance = new DrillAttendanceResponse();
            instance.drillId = this.drillId;
            instance.musterId = this.musterId;
            instance.voyageId = this.voyageId;
            instance.passengerId = this.passengerId;
            instance.drillDate = this.drillDate;
            instance.attendanceStatus = this.attendanceStatus;
            instance.recordedById = this.recordedById;
            return instance;
        }
    }

}
