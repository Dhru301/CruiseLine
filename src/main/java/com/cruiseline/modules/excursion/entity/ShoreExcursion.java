package com.cruiseline.modules.excursion.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.DifficultyLevel;
import com.cruiseline.common.enums.ExcursionCategory;
import com.cruiseline.common.enums.ExcursionStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "shore_excursions", indexes = @Index(name = "idx_excursion_port", columnList = "port_of_call"))
public class ShoreExcursion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    private Long excursionId;

    @Column(name = "port_of_call", nullable = false, length = 100)
    private String portOfCall;

    @Column(nullable = false, length = 150)
    private String excursionName;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ExcursionCategory category;

    private Double durationHours;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Column(nullable = false)
    private Integer bookedCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private DifficultyLevel difficultyLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private ExcursionStatus status = ExcursionStatus.AVAILABLE;

    public ShoreExcursion() {
    }

    public ShoreExcursion(Long excursionId, String portOfCall, String excursionName, ExcursionCategory category, Double durationHours, BigDecimal price, Integer maxCapacity, Integer bookedCount, DifficultyLevel difficultyLevel, ExcursionStatus status) {
        this.excursionId = excursionId;
        this.portOfCall = portOfCall;
        this.excursionName = excursionName;
        this.category = category;
        this.durationHours = durationHours;
        this.price = price;
        this.maxCapacity = maxCapacity;
        this.bookedCount = bookedCount;
        this.difficultyLevel = difficultyLevel;
        this.status = status;
    }

    public Long getExcursionId() {
        return this.excursionId;
    }

    public String getPortOfCall() {
        return this.portOfCall;
    }

    public String getExcursionName() {
        return this.excursionName;
    }

    public ExcursionCategory getCategory() {
        return this.category;
    }

    public Double getDurationHours() {
        return this.durationHours;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Integer getMaxCapacity() {
        return this.maxCapacity;
    }

    public Integer getBookedCount() {
        return this.bookedCount;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public ExcursionStatus getStatus() {
        return this.status;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

    public void setPortOfCall(String portOfCall) {
        this.portOfCall = portOfCall;
    }

    public void setExcursionName(String excursionName) {
        this.excursionName = excursionName;
    }

    public void setCategory(ExcursionCategory category) {
        this.category = category;
    }

    public void setDurationHours(Double durationHours) {
        this.durationHours = durationHours;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setBookedCount(Integer bookedCount) {
        this.bookedCount = bookedCount;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setStatus(ExcursionStatus status) {
        this.status = status;
    }

    public static ShoreExcursionBuilder builder() {
        return new ShoreExcursionBuilder();
    }

    public static class ShoreExcursionBuilder {
        private Long excursionId;
        private String portOfCall;
        private String excursionName;
        private ExcursionCategory category;
        private Double durationHours;
        private BigDecimal price;
        private Integer maxCapacity;
        private Integer bookedCount = 0;
        private DifficultyLevel difficultyLevel;
        private ExcursionStatus status = ExcursionStatus.AVAILABLE;

        public ShoreExcursionBuilder excursionId(Long excursionId) {
            this.excursionId = excursionId;
            return this;
        }
        public ShoreExcursionBuilder portOfCall(String portOfCall) {
            this.portOfCall = portOfCall;
            return this;
        }
        public ShoreExcursionBuilder excursionName(String excursionName) {
            this.excursionName = excursionName;
            return this;
        }
        public ShoreExcursionBuilder category(ExcursionCategory category) {
            this.category = category;
            return this;
        }
        public ShoreExcursionBuilder durationHours(Double durationHours) {
            this.durationHours = durationHours;
            return this;
        }
        public ShoreExcursionBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }
        public ShoreExcursionBuilder maxCapacity(Integer maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }
        public ShoreExcursionBuilder bookedCount(Integer bookedCount) {
            this.bookedCount = bookedCount;
            return this;
        }
        public ShoreExcursionBuilder difficultyLevel(DifficultyLevel difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
            return this;
        }
        public ShoreExcursionBuilder status(ExcursionStatus status) {
            this.status = status;
            return this;
        }

        public ShoreExcursion build() {
            ShoreExcursion instance = new ShoreExcursion();
            instance.excursionId = this.excursionId;
            instance.portOfCall = this.portOfCall;
            instance.excursionName = this.excursionName;
            instance.category = this.category;
            instance.durationHours = this.durationHours;
            instance.price = this.price;
            instance.maxCapacity = this.maxCapacity;
            instance.bookedCount = this.bookedCount;
            instance.difficultyLevel = this.difficultyLevel;
            instance.status = this.status;
            return instance;
        }
    }

}
