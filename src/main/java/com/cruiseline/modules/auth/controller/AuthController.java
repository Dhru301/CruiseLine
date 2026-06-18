package com.cruiseline.modules.auth.controller;

import com.cruiseline.common.dto.ApiResponse;
import com.cruiseline.modules.auth.dto.*;
import com.cruiseline.modules.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Login, registration and token refresh") /* — Swagger/OpenAPI documentation; it groups these
																						endpoints under "Authentication" in the Swagger UI */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new account (defaults to PASSENGER)")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Registration successful", authService.register(req)));
    }

    @Operation(summary = "Authenticate and obtain JWT tokens")
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return ApiResponse.ok("Login successful", authService.login(req));
    }

    @Operation(summary = "Exchange a refresh token for a new access token")
    @PostMapping("/refresh-token")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest req) {
        return ApiResponse.ok(authService.refreshToken(req));
    }
}