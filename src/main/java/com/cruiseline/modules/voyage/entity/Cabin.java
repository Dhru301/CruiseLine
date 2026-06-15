package com.cruiseline.modules.voyage.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.CabinLocation;
import com.cruiseline.common.enums.CabinStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "cabins", indexes = @Index(name = "idx_cabin_category", columnList = "category_id"))
public class Cabin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabin_id")
    private Long cabinId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CabinCategory category;

    @Column(nullable = false, length = 20)
    private String cabinNumber;

    @Column(length = 30)
    private String deck;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private CabinLocation location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private CabinStatus status = CabinStatus.AVAILABLE;

    public Cabin() {
    }

    public Cabin(Long cabinId, CabinCategory category, String cabinNumber, String deck, CabinLocation location, CabinStatus status) {
        this.cabinId = cabinId;
        this.category = category;
        this.cabinNumber = cabinNumber;
        this.deck = deck;
        this.location = location;
        this.status = status;
    }

    public Long getCabinId() {
        return this.cabinId;
    }

    public CabinCategory getCategory() {
        return this.category;
    }

    public String getCabinNumber() {
        return this.cabinNumber;
    }

    public String getDeck() {
        return this.deck;
    }

    public CabinLocation getLocation() {
        return this.location;
    }

    public CabinStatus getStatus() {
        return this.status;
    }

    public void setCabinId(Long cabinId) {
        this.cabinId = cabinId;
    }

    public void setCategory(CabinCategory category) {
        this.category = category;
    }

    public void setCabinNumber(String cabinNumber) {
        this.cabinNumber = cabinNumber;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public void setLocation(CabinLocation location) {
        this.location = location;
    }

    public void setStatus(CabinStatus status) {
        this.status = status;
    }

    public static CabinBuilder builder() {
        return new CabinBuilder();
    }

    public static class CabinBuilder {
        private Long cabinId;
        private CabinCategory category;
        private String cabinNumber;
        private String deck;
        private CabinLocation location;
        private CabinStatus status = CabinStatus.AVAILABLE;

        public CabinBuilder cabinId(Long cabinId) {
            this.cabinId = cabinId;
            return this;
        }
        public CabinBuilder category(CabinCategory category) {
            this.category = category;
            return this;
        }
        public CabinBuilder cabinNumber(String cabinNumber) {
            this.cabinNumber = cabinNumber;
            return this;
        }
        public CabinBuilder deck(String deck) {
            this.deck = deck;
            return this;
        }
        public CabinBuilder location(CabinLocation location) {
            this.location = location;
            return this;
        }
        public CabinBuilder status(CabinStatus status) {
            this.status = status;
            return this;
        }

        public Cabin build() {
            Cabin instance = new Cabin();
            instance.cabinId = this.cabinId;
            instance.category = this.category;
            instance.cabinNumber = this.cabinNumber;
            instance.deck = this.deck;
            instance.location = this.location;
            instance.status = this.status;
            return instance;
        }
    }

}
