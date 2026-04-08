package com.careerguidance.platform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerguidance.platform.dto.StudentSummaryResponse;
import com.careerguidance.platform.service.StudentService;

@RestController
@RequestMapping("/api/admin")
public class AdminManagementController {

    private final StudentService studentService;

    public AdminManagementController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentSummaryResponse> getStudents() {
        return studentService.getAllStudents();
    }
}
