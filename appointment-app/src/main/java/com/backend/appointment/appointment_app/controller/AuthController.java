package com.backend.appointment.appointment_app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.TokenResponse;
import com.backend.appointment.appointment_app.dto.UserDto;
import com.backend.appointment.appointment_app.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api-version.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

     @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody UserDto registerRequest) {
        final TokenResponse response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody UserDto loginRequest) {
        final TokenResponse response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return authService.refreshToken(authentication);
    }
}
