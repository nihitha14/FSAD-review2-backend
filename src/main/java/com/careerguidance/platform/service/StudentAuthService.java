package com.careerguidance.platform.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.AuthResponse;
import com.careerguidance.platform.dto.StudentLoginRequest;
import com.careerguidance.platform.dto.StudentRegisterRequest;
import com.careerguidance.platform.dto.StudentResponse;
import com.careerguidance.platform.exception.BadRequestException;
import com.careerguidance.platform.model.Student;
import com.careerguidance.platform.repository.StudentRepository;

@Service
public class StudentAuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentAuthService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(StudentRegisterRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        if (studentRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new BadRequestException("An account already exists with this email.");
        }

        Student student = new Student();
        student.setFullName(request.fullName());
        student.setEmail(normalizedEmail);
        student.setPasswordHash(passwordEncoder.encode(request.password()));
        student.setCollegeName(request.collegeName());
        student.setBranch(request.branch());
        student.setGraduationYear(request.graduationYear());
        student.setCareerInterest(request.careerInterest());
        student.setCreatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(student);
        return new AuthResponse("Student account created successfully.", mapToResponse(savedStudent));
    }

    public AuthResponse login(StudentLoginRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        Student student = studentRepository.findByEmailIgnoreCase(normalizedEmail)
                .orElseThrow(() -> new BadRequestException("Invalid email or password."));

        if (!passwordEncoder.matches(request.password(), student.getPasswordHash())) {
            throw new BadRequestException("Invalid email or password.");
        }

        return new AuthResponse("Login successful.", mapToResponse(student));
    }

    private StudentResponse mapToResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFullName(),
                student.getEmail(),
                student.getCollegeName(),
                student.getBranch(),
                student.getGraduationYear(),
                student.getCareerInterest(),
                student.getCreatedAt()
        );
    }
}
