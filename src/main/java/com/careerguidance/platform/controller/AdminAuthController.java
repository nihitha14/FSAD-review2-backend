package com.careerguidance.platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.careerguidance.platform.dto.AdminAuthResponse;
import com.careerguidance.platform.dto.AdminLoginRequest;
import com.careerguidance.platform.dto.AdminRegisterRequest;
import com.careerguidance.platform.service.AdminAuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin-auth")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminAuthResponse register(@Valid @RequestBody AdminRegisterRequest request) {
        return adminAuthService.register(request);
    }

    @PostMapping("/login")
    public AdminAuthResponse login(@Valid @RequestBody AdminLoginRequest request) {
        return adminAuthService.login(request);
    }
}
