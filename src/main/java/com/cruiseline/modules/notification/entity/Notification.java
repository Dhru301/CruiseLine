package com.cruiseline.modules.notification.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.NotificationCategory;
import com.cruiseline.common.enums.NotificationStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "notifications", indexes = @Index(name = "idx_notif_user", columnList = "user_id"))
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 500)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private NotificationStatus status = NotificationStatus.UNREAD;

    @Column(nullable = false)
    private Instant createdDate;

    @PrePersist
    void onCreate() {
        if (createdDate == null) createdDate = Instant.now();
    }

    public Notification() {
    }

    public Notification(Long notificationId, Long userId, String message, NotificationCategory category, NotificationStatus status, Instant createdDate) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.category = category;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Long getNotificationId() {
        return this.notificationId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getMessage() {
        return this.message;
    }

    public NotificationCategory getCategory() {
        return this.category;
    }

    public NotificationStatus getStatus() {
        return this.status;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCategory(NotificationCategory category) {
        this.category = category;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public static NotificationBuilder builder() {
        return new NotificationBuilder();
    }

    public static class NotificationBuilder {
        private Long notificationId;
        private Long userId;
        private String message;
        private NotificationCategory category;
        private NotificationStatus status = NotificationStatus.UNREAD;
        private Instant createdDate;

        public NotificationBuilder notificationId(Long notificationId) {
            this.notificationId = notificationId;
            return this;
        }
        public NotificationBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public NotificationBuilder message(String message) {
            this.message = message;
            return this;
        }
        public NotificationBuilder category(NotificationCategory category) {
            this.category = category;
            return this;
        }
        public NotificationBuilder status(NotificationStatus status) {
            this.status = status;
            return this;
        }
        public NotificationBuilder createdDate(Instant createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Notification build() {
            Notification instance = new Notification();
            instance.notificationId = this.notificationId;
            instance.userId = this.userId;
            instance.message = this.message;
            instance.category = this.category;
            instance.status = this.status;
            instance.createdDate = this.createdDate;
            return instance;
        }
    }

}
