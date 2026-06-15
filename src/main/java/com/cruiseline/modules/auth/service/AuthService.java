package com.cruiseline.modules.auth.service;

import com.cruiseline.common.audit.AuditService;
import com.cruiseline.common.enums.Role;
import com.cruiseline.common.enums.UserStatus;
import com.cruiseline.config.JwtTokenProvider;
import com.cruiseline.config.UserPrincipal;
import com.cruiseline.exception.BadRequestException;
import com.cruiseline.exception.DuplicateResourceException;
import com.cruiseline.modules.auth.dto.*;
import com.cruiseline.modules.auth.entity.User;
import com.cruiseline.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final AuditService auditService;

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + req.getEmail());
        }
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(Role.PASSENGER)   // public sign-up is ALWAYS a passenger
                .status(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
        auditService.record("USER_REGISTERED", "User");
        return buildAuthResponse(new UserPrincipal(user));
    }

    public AuthResponse login(LoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return buildAuthResponse(principal);
    }

    public AuthResponse refreshToken(RefreshTokenRequest req) {
        String refreshToken = req.getRefreshToken();
        if (!tokenProvider.validate(refreshToken) || !tokenProvider.isRefreshToken(refreshToken)) {
            throw new BadRequestException("Invalid or expired refresh token");
        }
        String email = tokenProvider.getEmailFromToken(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User no longer exists"));
        return buildAuthResponse(new UserPrincipal(user));
    }

    private AuthResponse buildAuthResponse(UserPrincipal principal) {
        return AuthResponse.builder()
                .accessToken(tokenProvider.generateAccessToken(principal))
                .refreshToken(tokenProvider.generateRefreshToken(principal))
                .tokenType("Bearer")
                .expiresInMs(tokenProvider.getAccessExpirationMs())
                .userId(principal.getUserId())
                .name(principal.getName())
                .email(principal.getEmail())
                .role(principal.getRole())
                .build();
    }
}