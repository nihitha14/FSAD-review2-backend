package com.careerguidance.platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResourceRequest(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Type is required")
        String type,
        @NotBlank(message = "Description is required")
        String description,
        @NotBlank(message = "Resource URL is required")
        String url,
        @NotBlank(message = "Duration is required")
        String duration,
        @NotBlank(message = "Level is required")
        String level,
        @NotNull(message = "Career path selection is required")
        Long careerPathId
) {
}
