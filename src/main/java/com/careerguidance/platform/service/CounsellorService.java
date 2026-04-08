package com.careerguidance.platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.CounsellorRequest;
import com.careerguidance.platform.dto.CounsellorResponse;
import com.careerguidance.platform.exception.ResourceNotFoundException;
import com.careerguidance.platform.model.Counsellor;
import com.careerguidance.platform.repository.CounsellorRepository;

@Service
public class CounsellorService {

    private final CounsellorRepository counsellorRepository;

    public CounsellorService(CounsellorRepository counsellorRepository) {
        this.counsellorRepository = counsellorRepository;
    }

    public List<CounsellorResponse> getCounsellors(String specialization) {
        List<Counsellor> counsellors;

        if (specialization != null && !specialization.isBlank()) {
            counsellors = counsellorRepository.findBySpecializationContainingIgnoreCase(specialization);
        } else {
            counsellors = counsellorRepository.findAll();
        }

        return counsellors.stream().map(this::mapToResponse).toList();
    }

    public CounsellorResponse getCounsellorById(Long id) {
        return mapToResponse(getCounsellorEntity(id));
    }

    public CounsellorResponse createCounsellor(CounsellorRequest request) {
        Counsellor counsellor = new Counsellor();
        applyRequest(counsellor, request);
        return mapToResponse(counsellorRepository.save(counsellor));
    }

    public CounsellorResponse updateCounsellor(Long id, CounsellorRequest request) {
        Counsellor counsellor = getCounsellorEntity(id);
        applyRequest(counsellor, request);
        return mapToResponse(counsellorRepository.save(counsellor));
    }

    public void deleteCounsellor(Long id) {
        counsellorRepository.delete(getCounsellorEntity(id));
    }

    public Counsellor getCounsellorEntity(Long id) {
        return counsellorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Counsellor not found with id " + id));
    }

    private void applyRequest(Counsellor counsellor, CounsellorRequest request) {
        counsellor.setFullName(request.fullName());
        counsellor.setSpecialization(request.specialization());
        counsellor.setExperienceYears(request.experienceYears());
        counsellor.setProfileSummary(request.profileSummary());
        counsellor.setLanguages(request.languages());
        counsellor.setEmail(request.email());
        counsellor.setAvailableDays(request.availableDays());
        counsellor.setSessionFee(request.sessionFee());
        counsellor.setImageUrl(request.imageUrl());
        counsellor.setRating(request.rating());
    }

    private CounsellorResponse mapToResponse(Counsellor counsellor) {
        return new CounsellorResponse(
                counsellor.getId(),
                counsellor.getFullName(),
                counsellor.getSpecialization(),
                counsellor.getExperienceYears(),
                counsellor.getProfileSummary(),
                counsellor.getLanguages(),
                counsellor.getEmail(),
                counsellor.getAvailableDays(),
                counsellor.getSessionFee(),
                counsellor.getImageUrl(),
                counsellor.getRating()
        );
    }
}
