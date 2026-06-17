package com.cts.CruiseLine.dto;


public class CheckInRequest {
    
    private Long passengerId;
    
    private Long voyageId;
    
    private Boolean documentVerified;
    private Long musterStationId;

    public Long getPassengerId() {
        return this.passengerId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public Boolean getDocumentVerified() {
        return this.documentVerified;
    }

    public Long getMusterStationId() {
        return this.musterStationId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setDocumentVerified(Boolean documentVerified) {
        this.documentVerified = documentVerified;
    }

    public void setMusterStationId(Long musterStationId) {
        this.musterStationId = musterStationId;
    }

}
