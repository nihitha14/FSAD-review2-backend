package com.careerguidance.platform.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.AdminAuthResponse;
import com.careerguidance.platform.dto.AdminLoginRequest;
import com.careerguidance.platform.dto.AdminRegisterRequest;
import com.careerguidance.platform.dto.AdminResponse;
import com.careerguidance.platform.exception.BadRequestException;
import com.careerguidance.platform.model.Admin;
import com.careerguidance.platform.repository.AdminRepository;

@Service
public class AdminAuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminAuthResponse register(AdminRegisterRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        if (adminRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new BadRequestException("An admin account already exists with this email.");
        }

        Admin admin = new Admin();
        admin.setFullName(request.fullName());
        admin.setEmail(normalizedEmail);
        admin.setPasswordHash(passwordEncoder.encode(request.password()));
        admin.setDepartment(request.department());
        admin.setDesignation(request.designation());
        admin.setCreatedAt(LocalDateTime.now());

        Admin savedAdmin = adminRepository.save(admin);
        return new AdminAuthResponse("Admin account created successfully.", mapToResponse(savedAdmin));
    }

    public AdminAuthResponse login(AdminLoginRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        Admin admin = adminRepository.findByEmailIgnoreCase(normalizedEmail)
                .orElseThrow(() -> new BadRequestException("Invalid admin email or password."));

        if (!passwordEncoder.matches(request.password(), admin.getPasswordHash())) {
            throw new BadRequestException("Invalid admin email or password.");
        }

        return new AdminAuthResponse("Admin login successful.", mapToResponse(admin));
    }

    private AdminResponse mapToResponse(Admin admin) {
        return new AdminResponse(
                admin.getId(),
                admin.getFullName(),
                admin.getEmail(),
                admin.getDepartment(),
                admin.getDesignation(),
                admin.getCreatedAt()
        );
    }
}
