package com.careerguidance.platform.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CounsellorRequest(
        @NotBlank(message = "Name is required")
        String fullName,
        @NotBlank(message = "Specialization is required")
        String specialization,
        @PositiveOrZero(message = "Experience should be valid")
        int experienceYears,
        @NotBlank(message = "Profile summary is required")
        String profileSummary,
        @NotBlank(message = "Languages are required")
        String languages,
        @Email(message = "Enter a valid email")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Availability is required")
        String availableDays,
        @NotNull(message = "Session fee is required")
        Double sessionFee,
        @NotBlank(message = "Image URL is required")
        String imageUrl,
        @NotNull(message = "Rating is required")
        @DecimalMin(value = "1.0", message = "Rating should be at least 1")
        @DecimalMax(value = "5.0", message = "Rating should not exceed 5")
        Double rating
) {
}
