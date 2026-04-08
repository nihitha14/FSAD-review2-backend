package com.careerguidance.platform.dto;

import com.careerguidance.platform.model.SessionStatus;

import jakarta.validation.constraints.NotNull;

public record SessionStatusUpdateRequest(
        @NotNull(message = "Status is required")
        SessionStatus status
) {
}
