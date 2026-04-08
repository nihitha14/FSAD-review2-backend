package com.careerguidance.platform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminRegisterRequest(
        @NotBlank(message = "Full name is required")
        String fullName,
        @Email(message = "Enter a valid email")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password should contain at least 6 characters")
        String password,
        @NotBlank(message = "Department is required")
        String department,
        @NotBlank(message = "Designation is required")
        String designation
) {
}
