package com.cruiseline.modules.auth.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.Role;
import com.cruiseline.common.enums.UserStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @Column(nullable = false)
    private String password;   // BCrypt hash

    @Column(length = 30)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @Column(name = "vessel_id", length = 40)
    private String vesselId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private UserStatus status = UserStatus.ACTIVE;

    public User() {
    }

    public User(Long userId, String name, String email, String password, String phone, Role role, String vesselId, UserStatus status) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.vesselId = vesselId;
        this.status = status;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public Role getRole() {
        return this.role;
    }

    public String getVesselId() {
        return this.vesselId;
    }

    public UserStatus getStatus() {
        return this.status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setVesselId(String vesselId) {
        this.vesselId = vesselId;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long userId;
        private String name;
        private String email;
        private String password;
        private String phone;
        private Role role;
        private String vesselId;
        private UserStatus status = UserStatus.ACTIVE;

        public UserBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }
        public UserBuilder vesselId(String vesselId) {
            this.vesselId = vesselId;
            return this;
        }
        public UserBuilder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public User build() {
            User instance = new User();
            instance.userId = this.userId;
            instance.name = this.name;
            instance.email = this.email;
            instance.password = this.password;
            instance.phone = this.phone;
            instance.role = this.role;
            instance.vesselId = this.vesselId;
            instance.status = this.status;
            return instance;
        }
    }

}
