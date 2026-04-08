package com.careerguidance.platform.dto;

import java.util.List;

public record DashboardStatsResponse(
        long totalStudents,
        long totalCareerPaths,
        long totalResources,
        long totalCounsellors,
        long totalSessions,
        long pendingSessions,
        long completedSessions,
        List<SessionResponse> recentSessions
) {
}
