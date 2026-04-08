package com.careerguidance.platform.dto;

public record CounsellorResponse(
        Long id,
        String fullName,
        String specialization,
        int experienceYears,
        String profileSummary,
        String languages,
        String email,
        String availableDays,
        Double sessionFee,
        String imageUrl,
        Double rating
) {
}
