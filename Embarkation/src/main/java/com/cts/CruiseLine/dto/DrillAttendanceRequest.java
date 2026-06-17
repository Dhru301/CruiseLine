package com.cts.CruiseLine.dto;

import com.cts.CruiseLine.enums.AttendanceStatus;

import java.time.LocalDate;

public class DrillAttendanceRequest {
    
    private Long musterId;
    
    private Long voyageId;
    
    private Long passengerId;
    
    private LocalDate drillDate;
    
    private AttendanceStatus attendanceStatus;

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

}
