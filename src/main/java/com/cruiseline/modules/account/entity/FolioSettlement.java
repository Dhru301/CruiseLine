package com.cruiseline.modules.account.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.PaymentMode;
import com.cruiseline.common.enums.SettlementStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "folio_settlements", indexes = @Index(name = "idx_settle_account", columnList = "account_id"))
public class FolioSettlement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_id")
    private Long settlementId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalCharges;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalCredits;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal balanceSettled;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PaymentMode paymentMode;

    @Column(nullable = false)
    private Instant settlementDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SettlementStatus status = SettlementStatus.SETTLED;

    @PrePersist
    void onCreate() {
        if (settlementDate == null) settlementDate = Instant.now();
    }

    public FolioSettlement() {
    }

    public FolioSettlement(Long settlementId, Long accountId, BigDecimal totalCharges, BigDecimal totalCredits, BigDecimal balanceSettled, PaymentMode paymentMode, Instant settlementDate, SettlementStatus status) {
        this.settlementId = settlementId;
        this.accountId = accountId;
        this.totalCharges = totalCharges;
        this.totalCredits = totalCredits;
        this.balanceSettled = balanceSettled;
        this.paymentMode = paymentMode;
        this.settlementDate = settlementDate;
        this.status = status;
    }

    public Long getSettlementId() {
        return this.settlementId;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public BigDecimal getTotalCharges() {
        return this.totalCharges;
    }

    public BigDecimal getTotalCredits() {
        return this.totalCredits;
    }

    public BigDecimal getBalanceSettled() {
        return this.balanceSettled;
    }

    public PaymentMode getPaymentMode() {
        return this.paymentMode;
    }

    public Instant getSettlementDate() {
        return this.settlementDate;
    }

    public SettlementStatus getStatus() {
        return this.status;
    }

    public void setSettlementId(Long settlementId) {
        this.settlementId = settlementId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setTotalCharges(BigDecimal totalCharges) {
        this.totalCharges = totalCharges;
    }

    public void setTotalCredits(BigDecimal totalCredits) {
        this.totalCredits = totalCredits;
    }

    public void setBalanceSettled(BigDecimal balanceSettled) {
        this.balanceSettled = balanceSettled;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setSettlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
    }

    public void setStatus(SettlementStatus status) {
        this.status = status;
    }

    public static FolioSettlementBuilder builder() {
        return new FolioSettlementBuilder();
    }

    public static class FolioSettlementBuilder {
        private Long settlementId;
        private Long accountId;
        private BigDecimal totalCharges;
        private BigDecimal totalCredits;
        private BigDecimal balanceSettled;
        private PaymentMode paymentMode;
        private Instant settlementDate;
        private SettlementStatus status = SettlementStatus.SETTLED;

        public FolioSettlementBuilder settlementId(Long settlementId) {
            this.settlementId = settlementId;
            return this;
        }
        public FolioSettlementBuilder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }
        public FolioSettlementBuilder totalCharges(BigDecimal totalCharges) {
            this.totalCharges = totalCharges;
            return this;
        }
        public FolioSettlementBuilder totalCredits(BigDecimal totalCredits) {
            this.totalCredits = totalCredits;
            return this;
        }
        public FolioSettlementBuilder balanceSettled(BigDecimal balanceSettled) {
            this.balanceSettled = balanceSettled;
            return this;
        }
        public FolioSettlementBuilder paymentMode(PaymentMode paymentMode) {
            this.paymentMode = paymentMode;
            return this;
        }
        public FolioSettlementBuilder settlementDate(Instant settlementDate) {
            this.settlementDate = settlementDate;
            return this;
        }
        public FolioSettlementBuilder status(SettlementStatus status) {
            this.status = status;
            return this;
        }

        public FolioSettlement build() {
            FolioSettlement instance = new FolioSettlement();
            instance.settlementId = this.settlementId;
            instance.accountId = this.accountId;
            instance.totalCharges = this.totalCharges;
            instance.totalCredits = this.totalCredits;
            instance.balanceSettled = this.balanceSettled;
            instance.paymentMode = this.paymentMode;
            instance.settlementDate = this.settlementDate;
            instance.status = this.status;
            return instance;
        }
    }

}
