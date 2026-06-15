package com.cruiseline.modules.account.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.ChargeStatus;
import com.cruiseline.common.enums.ChargeType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "onboard_charges", indexes = @Index(name = "idx_charge_account", columnList = "account_id"))
public class OnboardCharge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_id")
    private Long chargeId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ChargeType chargeType;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant postedDateTime;

    @Column(name = "posted_by_id")
    private Long postedById;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private ChargeStatus status = ChargeStatus.POSTED;

    @PrePersist
    void onCreate() {
        if (postedDateTime == null) postedDateTime = Instant.now();
    }

    public OnboardCharge() {
    }

    public OnboardCharge(Long chargeId, Long accountId, ChargeType chargeType, String description, BigDecimal amount, Instant postedDateTime, Long postedById, ChargeStatus status) {
        this.chargeId = chargeId;
        this.accountId = accountId;
        this.chargeType = chargeType;
        this.description = description;
        this.amount = amount;
        this.postedDateTime = postedDateTime;
        this.postedById = postedById;
        this.status = status;
    }

    public Long getChargeId() {
        return this.chargeId;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public ChargeType getChargeType() {
        return this.chargeType;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Instant getPostedDateTime() {
        return this.postedDateTime;
    }

    public Long getPostedById() {
        return this.postedById;
    }

    public ChargeStatus getStatus() {
        return this.status;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setChargeType(ChargeType chargeType) {
        this.chargeType = chargeType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPostedDateTime(Instant postedDateTime) {
        this.postedDateTime = postedDateTime;
    }

    public void setPostedById(Long postedById) {
        this.postedById = postedById;
    }

    public void setStatus(ChargeStatus status) {
        this.status = status;
    }

    public static OnboardChargeBuilder builder() {
        return new OnboardChargeBuilder();
    }

    public static class OnboardChargeBuilder {
        private Long chargeId;
        private Long accountId;
        private ChargeType chargeType;
        private String description;
        private BigDecimal amount;
        private Instant postedDateTime;
        private Long postedById;
        private ChargeStatus status = ChargeStatus.POSTED;

        public OnboardChargeBuilder chargeId(Long chargeId) {
            this.chargeId = chargeId;
            return this;
        }
        public OnboardChargeBuilder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }
        public OnboardChargeBuilder chargeType(ChargeType chargeType) {
            this.chargeType = chargeType;
            return this;
        }
        public OnboardChargeBuilder description(String description) {
            this.description = description;
            return this;
        }
        public OnboardChargeBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }
        public OnboardChargeBuilder postedDateTime(Instant postedDateTime) {
            this.postedDateTime = postedDateTime;
            return this;
        }
        public OnboardChargeBuilder postedById(Long postedById) {
            this.postedById = postedById;
            return this;
        }
        public OnboardChargeBuilder status(ChargeStatus status) {
            this.status = status;
            return this;
        }

        public OnboardCharge build() {
            OnboardCharge instance = new OnboardCharge();
            instance.chargeId = this.chargeId;
            instance.accountId = this.accountId;
            instance.chargeType = this.chargeType;
            instance.description = this.description;
            instance.amount = this.amount;
            instance.postedDateTime = this.postedDateTime;
            instance.postedById = this.postedById;
            instance.status = this.status;
            return instance;
        }
    }

}
