package com.careerguidance.platform.service;

import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.DashboardStatsResponse;
import com.careerguidance.platform.model.SessionStatus;
import com.careerguidance.platform.repository.CareerPathRepository;
import com.careerguidance.platform.repository.CareerResourceRepository;
import com.careerguidance.platform.repository.CounsellorRepository;
import com.careerguidance.platform.repository.CounsellingSessionRepository;
import com.careerguidance.platform.repository.StudentRepository;

@Service
public class DashboardService {

    private final CareerPathRepository careerPathRepository;
    private final CareerResourceRepository careerResourceRepository;
    private final CounsellorRepository counsellorRepository;
    private final CounsellingSessionRepository counsellingSessionRepository;
    private final StudentRepository studentRepository;
    private final SessionService sessionService;

    public DashboardService(
            CareerPathRepository careerPathRepository,
            CareerResourceRepository careerResourceRepository,
            CounsellorRepository counsellorRepository,
            CounsellingSessionRepository counsellingSessionRepository,
            StudentRepository studentRepository,
            SessionService sessionService
    ) {
        this.careerPathRepository = careerPathRepository;
        this.careerResourceRepository = careerResourceRepository;
        this.counsellorRepository = counsellorRepository;
        this.counsellingSessionRepository = counsellingSessionRepository;
        this.studentRepository = studentRepository;
        this.sessionService = sessionService;
    }

    public DashboardStatsResponse getStats() {
        return new DashboardStatsResponse(
                studentRepository.count(),
                careerPathRepository.count(),
                careerResourceRepository.count(),
                counsellorRepository.count(),
                counsellingSessionRepository.count(),
                counsellingSessionRepository.countByStatus(SessionStatus.PENDING),
                counsellingSessionRepository.countByStatus(SessionStatus.COMPLETED),
                sessionService.getRecentSessions()
        );
    }
}
