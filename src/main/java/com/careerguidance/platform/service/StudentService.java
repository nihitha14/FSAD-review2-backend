package com.careerguidance.platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.StudentSummaryResponse;
import com.careerguidance.platform.model.Student;
import com.careerguidance.platform.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentSummaryResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .sorted((first, second) -> second.getCreatedAt().compareTo(first.getCreatedAt()))
                .map(this::mapToSummary)
                .toList();
    }

    private StudentSummaryResponse mapToSummary(Student student) {
        return new StudentSummaryResponse(
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
