package com.careerguidance.platform.dto;

public record CareerPathResponse(
        Long id,
        String title,
        String category,
        String description,
        String requiredSkills,
        String roadmap,
        String averagePackage,
        String growthOutlook,
        String demandLevel,
        boolean featured,
        int studentsExplored
) {
}
