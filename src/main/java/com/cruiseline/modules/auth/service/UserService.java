package com.cruiseline.modules.auth.service;

import com.cruiseline.common.audit.AuditService;
import com.cruiseline.common.dto.PagedResponse;
import com.cruiseline.common.enums.Role;
import com.cruiseline.common.enums.UserStatus;
import com.cruiseline.exception.DuplicateResourceException;
import com.cruiseline.exception.ResourceNotFoundException;
import com.cruiseline.modules.auth.dto.RegisterRequest;
import com.cruiseline.modules.auth.dto.UpdateUserStatusRequest;
import com.cruiseline.modules.auth.dto.UserResponse;
import com.cruiseline.modules.auth.entity.User;
import com.cruiseline.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuditService auditService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + req.getEmail());
        }
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole() == null ? Role.PASSENGER : req.getRole())
                .vesselId(req.getVesselId())
                .status(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
        auditService.record("USER_CREATED_BY_ADMIN", "User");
        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public PagedResponse<UserResponse> listUsers(Role role, Pageable pageable) {
        var page = (role == null)
                ? userRepository.findAll(pageable).map(UserResponse::from)
                : userRepository.findByRole(role, pageable).map(UserResponse::from);
        return PagedResponse.from(page);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Transactional
    public UserResponse updateStatus(Long id, UpdateUserStatusRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setStatus(req.getStatus());
        userRepository.save(user);
        auditService.record("USER_STATUS_UPDATED", "User");
        return UserResponse.from(user);
    }
}