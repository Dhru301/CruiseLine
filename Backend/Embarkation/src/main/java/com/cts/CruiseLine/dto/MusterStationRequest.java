package com.cts.CruiseLine.dto;

public class MusterStationRequest {

    private Long voyageId;
    private String stationCode;
    private String deck;
    private String assignedCabinRange;
    private Integer capacity;

    public Long getVoyageId() { return voyageId; }
    public String getStationCode() { return stationCode; }
    public String getDeck() { return deck; }
    public String getAssignedCabinRange() { return assignedCabinRange; }
    public Integer getCapacity() { return capacity; }
    public void setVoyageId(Long voyageId) { this.voyageId = voyageId; }
    public void setStationCode(String stationCode) { this.stationCode = stationCode; }
    public void setDeck(String deck) { this.deck = deck; }
    public void setAssignedCabinRange(String assignedCabinRange) { this.assignedCabinRange = assignedCabinRange; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}