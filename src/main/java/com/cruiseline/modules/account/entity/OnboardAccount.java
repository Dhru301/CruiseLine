package com.cruiseline.modules.account.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.AccountStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "onboard_accounts", indexes = {
        @Index(name = "idx_acct_pax", columnList = "passenger_id"),
        @Index(name = "idx_acct_voyage", columnList = "voyage_id")
})
public class OnboardAccount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;

    @Column(name = "voyage_id", nullable = false)
    private Long voyageId;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal creditLimit = BigDecimal.ZERO;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal currentBalance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private AccountStatus status = AccountStatus.ACTIVE;

    public OnboardAccount() {
    }

    public OnboardAccount(Long accountId, Long passengerId, Long voyageId, BigDecimal creditLimit, BigDecimal currentBalance, AccountStatus status) {
        this.accountId = accountId;
        this.passengerId = passengerId;
        this.voyageId = voyageId;
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
        this.status = status;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public Long getPassengerId() {
        return this.passengerId;
    }

    public Long getVoyageId() {
        return this.voyageId;
    }

    public BigDecimal getCreditLimit() {
        return this.creditLimit;
    }

    public BigDecimal getCurrentBalance() {
        return this.currentBalance;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public static OnboardAccountBuilder builder() {
        return new OnboardAccountBuilder();
    }

    public static class OnboardAccountBuilder {
        private Long accountId;
        private Long passengerId;
        private Long voyageId;
        private BigDecimal creditLimit = BigDecimal.ZERO;
        private BigDecimal currentBalance = BigDecimal.ZERO;
        private AccountStatus status = AccountStatus.ACTIVE;

        public OnboardAccountBuilder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }
        public OnboardAccountBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public OnboardAccountBuilder voyageId(Long voyageId) {
            this.voyageId = voyageId;
            return this;
        }
        public OnboardAccountBuilder creditLimit(BigDecimal creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }
        public OnboardAccountBuilder currentBalance(BigDecimal currentBalance) {
            this.currentBalance = currentBalance;
            return this;
        }
        public OnboardAccountBuilder status(AccountStatus status) {
            this.status = status;
            return this;
        }

        public OnboardAccount build() {
            OnboardAccount instance = new OnboardAccount();
            instance.accountId = this.accountId;
            instance.passengerId = this.passengerId;
            instance.voyageId = this.voyageId;
            instance.creditLimit = this.creditLimit;
            instance.currentBalance = this.currentBalance;
            instance.status = this.status;
            return instance;
        }
    }

}
