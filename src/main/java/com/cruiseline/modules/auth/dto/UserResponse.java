package com.cruiseline.modules.auth.dto;

import lombok.*;
import com.cruiseline.common.enums.Role;
import com.cruiseline.common.enums.UserStatus;
import com.cruiseline.modules.auth.entity.User;

@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private String vesselId;
    private UserStatus status;

    // Lombok does NOT generate this — keep it yourself:
    public static UserResponse from(User u) {
        return UserResponse.builder()
                .userId(u.getUserId())
                .name(u.getName())
                .email(u.getEmail())
                .phone(u.getPhone())
                .role(u.getRole())
                .vesselId(u.getVesselId())
                .status(u.getStatus())
                .build();
    }
}