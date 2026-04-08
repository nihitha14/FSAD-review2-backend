package com.careerguidance.platform.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SessionRequest(
        @NotBlank(message = "Student name is required")
        String studentName,
        @Email(message = "Enter a valid email")
        @NotBlank(message = "Email is required")
        String studentEmail,
        @NotBlank(message = "College name is required")
        String collegeName,
        @NotBlank(message = "Branch is required")
        String branch,
        @NotBlank(message = "Interest area is required")
        String interestArea,
        @NotNull(message = "Preferred date is required")
        @FutureOrPresent(message = "Date must be today or later")
        LocalDate preferredDate,
        @NotBlank(message = "Preferred time slot is required")
        String preferredTimeSlot,
        @NotBlank(message = "Mode is required")
        String mode,
        @NotBlank(message = "Message is required")
        String message,
        @NotNull(message = "Please choose a counsellor")
        Long counsellorId
) {
}
