package com.careerguidance.platform.dto;

public record ResourceResponse(
        Long id,
        String title,
        String type,
        String description,
        String url,
        String duration,
        String level,
        Long careerPathId,
        String careerPathTitle
) {
}
