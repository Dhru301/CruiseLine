package com.cts.CruiseLine.entity;

import com.cts.CruiseLine.BaseEntity;
import com.cts.CruiseLine.enums.MusterStationStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "muster_stations", indexes = @Index(name = "idx_muster_voyage", columnList = "voyage_id"))
public class MusterStation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muster_id")
    private Long musterId;

    @Column(name = "voyage_id", nullable = false)
    private Long voyageId;

    @Column(nullable = false, length = 20)
    private String stationCode;

    @Column(length = 30)
    private String deck;

    @Column(length = 60)
    private String assignedCabinRange;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private MusterStationStatus status = MusterStationStatus.ACTIVE;

    public MusterStation() {
    }

    public MusterStation(Long musterId, Long voyageId, String stationCode, String deck, String assignedCabinRange, Integer capacity, MusterStationStatus status) {
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

    public static MusterStationBuilder builder() {
        return new MusterStationBuilder();
    }

    public static class MusterStationBuilder {
        private Long musterId;
        private Long voyageId;
        private String stationCode;
        private String deck;
        private String assignedCabinRange;
        private Integer capacity;
        private MusterStationStatus status = MusterStationStatus.ACTIVE;

        public MusterStationBuilder musterId(Long musterId) {
            this.musterId = musterId;
            return this;
        }
        public MusterStationBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public MusterStationBuilder stationCode(String stationCode) {
            this.stationCode = stationCode;
            return this;
        }
        public MusterStationBuilder deck(String deck) {
            this.deck = deck;
            return this;
        }
        public MusterStationBuilder assignedCabinRange(String assignedCabinRange) {
            this.assignedCabinRange = assignedCabinRange;
            return this;
        }
        public MusterStationBuilder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }
        public MusterStationBuilder status(MusterStationStatus status) {
            this.status = status;
            return this;
        }

        public MusterStation build() {
            MusterStation instance = new MusterStation();
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
