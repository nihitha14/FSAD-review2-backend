package com.careerguidance.platform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.SessionRequest;
import com.careerguidance.platform.dto.SessionResponse;
import com.careerguidance.platform.dto.SessionStatusUpdateRequest;
import com.careerguidance.platform.exception.ResourceNotFoundException;
import com.careerguidance.platform.model.CounsellingSession;
import com.careerguidance.platform.model.Counsellor;
import com.careerguidance.platform.model.SessionStatus;
import com.careerguidance.platform.repository.CounsellingSessionRepository;

@Service
public class SessionService {

    private final CounsellingSessionRepository counsellingSessionRepository;
    private final CounsellorService counsellorService;

    public SessionService(CounsellingSessionRepository counsellingSessionRepository, CounsellorService counsellorService) {
        this.counsellingSessionRepository = counsellingSessionRepository;
        this.counsellorService = counsellorService;
    }

    public List<SessionResponse> getSessions(SessionStatus status) {
        List<CounsellingSession> sessions = status == null
                ? counsellingSessionRepository.findAll()
                : counsellingSessionRepository.findByStatus(status);

        return sessions.stream()
                .sorted((first, second) -> second.getCreatedAt().compareTo(first.getCreatedAt()))
                .map(this::mapToResponse)
                .toList();
    }

    public List<SessionResponse> getRecentSessions() {
        return counsellingSessionRepository.findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SessionResponse createSession(SessionRequest request) {
        Counsellor counsellor = counsellorService.getCounsellorEntity(request.counsellorId());

        CounsellingSession session = new CounsellingSession();
        session.setStudentName(request.studentName());
        session.setStudentEmail(request.studentEmail());
        session.setCollegeName(request.collegeName());
        session.setBranch(request.branch());
        session.setInterestArea(request.interestArea());
        session.setPreferredDate(request.preferredDate());
        session.setPreferredTimeSlot(request.preferredTimeSlot());
        session.setMode(request.mode());
        session.setMessage(request.message());
        session.setStatus(SessionStatus.PENDING);
        session.setCreatedAt(LocalDateTime.now());
        session.setCounsellor(counsellor);

        return mapToResponse(counsellingSessionRepository.save(session));
    }

    public SessionResponse updateSessionStatus(Long id, SessionStatusUpdateRequest request) {
        CounsellingSession session = counsellingSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id " + id));

        session.setStatus(request.status());
        return mapToResponse(counsellingSessionRepository.save(session));
    }

    private SessionResponse mapToResponse(CounsellingSession session) {
        return new SessionResponse(
                session.getId(),
                session.getStudentName(),
                session.getStudentEmail(),
                session.getCollegeName(),
                session.getBranch(),
                session.getInterestArea(),
                session.getPreferredDate(),
                session.getPreferredTimeSlot(),
                session.getMode(),
                session.getMessage(),
                session.getStatus(),
                session.getCreatedAt(),
                session.getCounsellor().getId(),
                session.getCounsellor().getFullName()
        );
    }
}
