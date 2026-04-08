package com.careerguidance.platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CareerPathRequest(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Category is required")
        String category,
        @NotBlank(message = "Description is required")
        @Size(min = 20, message = "Description should be detailed")
        String description,
        @NotBlank(message = "Required skills are required")
        String requiredSkills,
        @NotBlank(message = "Roadmap is required")
        String roadmap,
        @NotBlank(message = "Average package is required")
        String averagePackage,
        @NotBlank(message = "Growth outlook is required")
        String growthOutlook,
        @NotBlank(message = "Demand level is required")
        String demandLevel,
        boolean featured,
        @PositiveOrZero(message = "Student count cannot be negative")
        int studentsExplored
) {
}
