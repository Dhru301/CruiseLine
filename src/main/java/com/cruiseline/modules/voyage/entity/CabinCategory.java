package com.cruiseline.modules.voyage.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.CabinCategoryName;
import com.cruiseline.common.enums.CategoryStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cabin_categories", indexes = @Index(name = "idx_cat_voyage", columnList = "voyage_id"))
public class CabinCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voyage_id", nullable = false)
    private Voyage voyage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CabinCategoryName categoryName;

    @Column(length = 30)
    private String deck;

    @Column(length = 60)
    private String beddingConfig;

    @Column(nullable = false)
    private Integer maxOccupancy;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private Integer totalCabins;

    @Column(nullable = false)
    private Integer availableCabins;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private CategoryStatus status = CategoryStatus.AVAILABLE;

    public CabinCategory() {
    }

    public CabinCategory(Long categoryId, Voyage voyage, CabinCategoryName categoryName, String deck, String beddingConfig, Integer maxOccupancy, BigDecimal basePrice, Integer totalCabins, Integer availableCabins, CategoryStatus status) {
        this.categoryId = categoryId;
        this.voyage = voyage;
        this.categoryName = categoryName;
        this.deck = deck;
        this.beddingConfig = beddingConfig;
        this.maxOccupancy = maxOccupancy;
        this.basePrice = basePrice;
        this.totalCabins = totalCabins;
        this.availableCabins = availableCabins;
        this.status = status;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public Voyage getVoyage() {
        return this.voyage;
    }

    public CabinCategoryName getCategoryName() {
        return this.categoryName;
    }

    public String getDeck() {
        return this.deck;
    }

    public String getBeddingConfig() {
        return this.beddingConfig;
    }

    public Integer getMaxOccupancy() {
        return this.maxOccupancy;
    }

    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    public Integer getTotalCabins() {
        return this.totalCabins;
    }

    public Integer getAvailableCabins() {
        return this.availableCabins;
    }

    public CategoryStatus getStatus() {
        return this.status;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public void setCategoryName(CabinCategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public void setBeddingConfig(String beddingConfig) {
        this.beddingConfig = beddingConfig;
    }

    public void setMaxOccupancy(Integer maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public void setTotalCabins(Integer totalCabins) {
        this.totalCabins = totalCabins;
    }

    public void setAvailableCabins(Integer availableCabins) {
        this.availableCabins = availableCabins;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public static CabinCategoryBuilder builder() {
        return new CabinCategoryBuilder();
    }

    public static class CabinCategoryBuilder {
        private Long categoryId;
        private Voyage voyage;
        private CabinCategoryName categoryName;
        private String deck;
        private String beddingConfig;
        private Integer maxOccupancy;
        private BigDecimal basePrice;
        private Integer totalCabins;
        private Integer availableCabins;
        private CategoryStatus status = CategoryStatus.AVAILABLE;

        public CabinCategoryBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }
        public CabinCategoryBuilder voyage(Voyage voyage) {
            this.voyage = voyage;
            return this;
        }
        public CabinCategoryBuilder categoryName(CabinCategoryName categoryName) {
            this.categoryName = categoryName;
            return this;
        }
        public CabinCategoryBuilder deck(String deck) {
            this.deck = deck;
            return this;
        }
        public CabinCategoryBuilder beddingConfig(String beddingConfig) {
            this.beddingConfig = beddingConfig;
            return this;
        }
        public CabinCategoryBuilder maxOccupancy(Integer maxOccupancy) {
            this.maxOccupancy = maxOccupancy;
            return this;
        }
        public CabinCategoryBuilder basePrice(BigDecimal basePrice) {
            this.basePrice = basePrice;
            return this;
        }
        public CabinCategoryBuilder totalCabins(Integer totalCabins) {
            this.totalCabins = totalCabins;
            return this;
        }
        public CabinCategoryBuilder availableCabins(Integer availableCabins) {
            this.availableCabins = availableCabins;
            return this;
        }
        public CabinCategoryBuilder status(CategoryStatus status) {
            this.status = status;
            return this;
        }

        public CabinCategory build() {
            CabinCategory instance = new CabinCategory();
            instance.categoryId = this.categoryId;
            instance.voyage = this.voyage;
            instance.categoryName = this.categoryName;
            instance.deck = this.deck;
            instance.beddingConfig = this.beddingConfig;
            instance.maxOccupancy = this.maxOccupancy;
            instance.basePrice = this.basePrice;
            instance.totalCabins = this.totalCabins;
            instance.availableCabins = this.availableCabins;
            instance.status = this.status;
            return instance;
        }
    }

}
