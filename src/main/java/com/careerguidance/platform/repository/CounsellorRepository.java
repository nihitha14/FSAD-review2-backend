package com.careerguidance.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerguidance.platform.model.Counsellor;

public interface CounsellorRepository extends JpaRepository<Counsellor, Long> {

    List<Counsellor> findBySpecializationContainingIgnoreCase(String specialization);
}
