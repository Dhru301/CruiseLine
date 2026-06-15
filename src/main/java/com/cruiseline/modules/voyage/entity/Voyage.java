package com.cruiseline.modules.voyage.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.VoyageStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "voyages", indexes = @Index(name = "idx_voyage_status", columnList = "status"))
public class Voyage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voyage_id")
    private Long voyageId;

    @Column(nullable = false, length = 150)
    private String voyageName;

    @Column(name = "vessel_id", nullable = false, length = 40)
    private String vesselId;

    @Column(nullable = false, length = 100)
    private String homePort;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private Integer durationNights;

    /** Comma-separated ports of call, e.g. "Lisbon,Casablanca,Funchal". */
    @Column(length = 1000)
    private String portsOfCall;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private VoyageStatus status = VoyageStatus.PLANNING;

    public Voyage() {
    }

    public Voyage(Long voyageId, String voyageName, String vesselId, String homePort, LocalDate departureDate, LocalDate returnDate, Integer durationNights, String portsOfCall, VoyageStatus status) {
        this.voyageId = voyageId;
        this.voyageName = voyageName;
        this.vesselId = vesselId;
        this.homePort = homePort;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.durationNights = durationNights;
        this.portsOfCall = portsOfCall;
        this.status = status;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public String getVoyageName() {
        return this.voyageName;
    }

    public String getVesselId() {
        return this.vesselId;
    }

    public String getHomePort() {
        return this.homePort;
    }

    public LocalDate getDepartureDate() {
        return this.departureDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public Integer getDurationNights() {
        return this.durationNights;
    }

    public String getPortsOfCall() {
        return this.portsOfCall;
    }

    public VoyageStatus getStatus() {
        return this.status;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setVoyageName(String voyageName) {
        this.voyageName = voyageName;
    }

    public void setVesselId(String vesselId) {
        this.vesselId = vesselId;
    }

    public void setHomePort(String homePort) {
        this.homePort = homePort;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setDurationNights(Integer durationNights) {
        this.durationNights = durationNights;
    }

    public void setPortsOfCall(String portsOfCall) {
        this.portsOfCall = portsOfCall;
    }

    public void setStatus(VoyageStatus status) {
        this.status = status;
    }

    public static VoyageBuilder builder() {
        return new VoyageBuilder();
    }

    public static class VoyageBuilder {
        private Long voyageId;
        private String voyageName;
        private String vesselId;
        private String homePort;
        private LocalDate departureDate;
        private LocalDate returnDate;
        private Integer durationNights;
        private String portsOfCall;
        private VoyageStatus status = VoyageStatus.PLANNING;

        public VoyageBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public VoyageBuilder voyageName(String voyageName) {
            this.voyageName = voyageName;
            return this;
        }
        public VoyageBuilder vesselId(String vesselId) {
            this.vesselId = vesselId;
            return this;
        }
        public VoyageBuilder homePort(String homePort) {
            this.homePort = homePort;
            return this;
        }
        public VoyageBuilder departureDate(LocalDate departureDate) {
            this.departureDate = departureDate;
            return this;
        }
        public VoyageBuilder returnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }
        public VoyageBuilder durationNights(Integer durationNights) {
            this.durationNights = durationNights;
            return this;
        }
        public VoyageBuilder portsOfCall(String portsOfCall) {
            this.portsOfCall = portsOfCall;
            return this;
        }
        public VoyageBuilder status(VoyageStatus status) {
            this.status = status;
            return this;
        }

        public Voyage build() {
            Voyage instance = new Voyage();
            instance.voyageId = this.voyageId;
            instance.voyageName = this.voyageName;
            instance.vesselId = this.vesselId;
            instance.homePort = this.homePort;
            instance.departureDate = this.departureDate;
            instance.returnDate = this.returnDate;
            instance.durationNights = this.durationNights;
            instance.portsOfCall = this.portsOfCall;
            instance.status = this.status;
            return instance;
        }
    }

}
