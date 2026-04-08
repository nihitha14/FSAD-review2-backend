package com.careerguidance.platform.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.careerguidance.platform.model.SessionStatus;

public record SessionResponse(
        Long id,
        String studentName,
        String studentEmail,
        String collegeName,
        String branch,
        String interestArea,
        LocalDate preferredDate,
        String preferredTimeSlot,
        String mode,
        String message,
        SessionStatus status,
        LocalDateTime createdAt,
        Long counsellorId,
        String counsellorName
) {
}
