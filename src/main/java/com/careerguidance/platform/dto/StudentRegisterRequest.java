package com.careerguidance.platform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentRegisterRequest(
        @NotBlank(message = "Full name is required")
        String fullName,
        @Email(message = "Enter a valid email")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password should contain at least 6 characters")
        String password,
        @NotBlank(message = "College name is required")
        String collegeName,
        @NotBlank(message = "Branch is required")
        String branch,
        @NotNull(message = "Graduation year is required")
        @Min(value = 2024, message = "Enter a valid graduation year")
        @Max(value = 2035, message = "Enter a valid graduation year")
        Integer graduationYear,
        @NotBlank(message = "Career interest is required")
        String careerInterest
) {
}
