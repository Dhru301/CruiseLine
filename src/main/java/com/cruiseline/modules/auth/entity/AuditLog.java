package com.cruiseline.modules.auth.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "audit_log", indexes = {
        @Index(name = "idx_audit_user", columnList = "user_id"),
        @Index(name = "idx_audit_entity", columnList = "entity_type")
})
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Long auditId;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 120)
    private String action;

    @Column(name = "entity_type", length = 80)
    private String entityType;

    @Column(nullable = false)
    private Instant timestamp;

    @PrePersist
    void onCreate() {
        if (timestamp == null) timestamp = Instant.now();
    }

    public AuditLog() {
    }

    public AuditLog(Long auditId, Long userId, String action, String entityType, Instant timestamp) {
        this.auditId = auditId;
        this.userId = userId;
        this.action = action;
        this.entityType = entityType;
        this.timestamp = timestamp;
    }

    public Long getAuditId() {
        return this.auditId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getAction() {
        return this.action;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static AuditLogBuilder builder() {
        return new AuditLogBuilder();
    }

    public static class AuditLogBuilder {
        private Long auditId;
        private Long userId;
        private String action;
        private String entityType;
        private Instant timestamp;

        public AuditLogBuilder auditId(Long auditId) {
            this.auditId = auditId;
            return this;
        }
        public AuditLogBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public AuditLogBuilder action(String action) {
            this.action = action;
            return this;
        }
        public AuditLogBuilder entityType(String entityType) {
            this.entityType = entityType;
            return this;
        }
        public AuditLogBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public AuditLog build() {
            AuditLog instance = new AuditLog();
            instance.auditId = this.auditId;
            instance.userId = this.userId;
            instance.action = this.action;
            instance.entityType = this.entityType;
            instance.timestamp = this.timestamp;
            return instance;
        }
    }

}
