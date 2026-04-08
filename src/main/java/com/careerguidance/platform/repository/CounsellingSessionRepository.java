package com.careerguidance.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerguidance.platform.model.CounsellingSession;
import com.careerguidance.platform.model.SessionStatus;

public interface CounsellingSessionRepository extends JpaRepository<CounsellingSession, Long> {

    List<CounsellingSession> findByStatus(SessionStatus status);

    long countByStatus(SessionStatus status);

    List<CounsellingSession> findTop5ByOrderByCreatedAtDesc();
}
