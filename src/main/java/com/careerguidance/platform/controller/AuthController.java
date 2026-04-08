package com.careerguidance.platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.careerguidance.platform.dto.AuthResponse;
import com.careerguidance.platform.dto.StudentLoginRequest;
import com.careerguidance.platform.dto.StudentRegisterRequest;
import com.careerguidance.platform.service.StudentAuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final StudentAuthService studentAuthService;

    public AuthController(StudentAuthService studentAuthService) {
        this.studentAuthService = studentAuthService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody StudentRegisterRequest request) {
        return studentAuthService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody StudentLoginRequest request) {
        return studentAuthService.login(request);
    }
}
