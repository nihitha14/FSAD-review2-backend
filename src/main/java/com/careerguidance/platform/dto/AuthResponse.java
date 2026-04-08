package com.careerguidance.platform.dto;

public record AuthResponse(
        String message,
        StudentResponse student
) {
}
