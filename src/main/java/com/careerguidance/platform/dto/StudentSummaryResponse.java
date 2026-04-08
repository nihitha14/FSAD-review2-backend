package com.careerguidance.platform.dto;

import java.time.LocalDateTime;

public record StudentSummaryResponse(
        Long id,
        String fullName,
        String email,
        String collegeName,
        String branch,
        Integer graduationYear,
        String careerInterest,
        LocalDateTime createdAt
) {
}
