package com.careerguidance.platform.dto;

import java.time.LocalDateTime;

public record AdminResponse(
        Long id,
        String fullName,
        String email,
        String department,
        String designation,
        LocalDateTime createdAt
) {
}
