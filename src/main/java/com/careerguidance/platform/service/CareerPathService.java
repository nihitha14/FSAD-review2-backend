package com.careerguidance.platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.CareerPathRequest;
import com.careerguidance.platform.dto.CareerPathResponse;
import com.careerguidance.platform.exception.ResourceNotFoundException;
import com.careerguidance.platform.model.CareerPath;
import com.careerguidance.platform.repository.CareerPathRepository;

@Service
public class CareerPathService {

    private final CareerPathRepository careerPathRepository;

    public CareerPathService(CareerPathRepository careerPathRepository) {
        this.careerPathRepository = careerPathRepository;
    }

    public List<CareerPathResponse> getCareerPaths(String keyword, Boolean featured) {
        List<CareerPath> careerPaths;

        if (Boolean.TRUE.equals(featured)) {
            careerPaths = careerPathRepository.findByFeaturedTrue();
        } else if (keyword != null && !keyword.isBlank()) {
            careerPaths = careerPathRepository
                    .findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                            keyword, keyword, keyword
                    );
        } else {
            careerPaths = careerPathRepository.findAll();
        }

        return careerPaths.stream().map(this::mapToResponse).toList();
    }

    public CareerPathResponse getCareerPathById(Long id) {
        return mapToResponse(getCareerPathEntity(id));
    }

    public CareerPathResponse createCareerPath(CareerPathRequest request) {
        CareerPath careerPath = new CareerPath();
        applyRequest(careerPath, request);
        return mapToResponse(careerPathRepository.save(careerPath));
    }

    public CareerPathResponse updateCareerPath(Long id, CareerPathRequest request) {
        CareerPath careerPath = getCareerPathEntity(id);
        applyRequest(careerPath, request);
        return mapToResponse(careerPathRepository.save(careerPath));
    }

    public void deleteCareerPath(Long id) {
        careerPathRepository.delete(getCareerPathEntity(id));
    }

    public CareerPath getCareerPathEntity(Long id) {
        return careerPathRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Career path not found with id " + id));
    }

    private void applyRequest(CareerPath careerPath, CareerPathRequest request) {
        careerPath.setTitle(request.title());
        careerPath.setCategory(request.category());
        careerPath.setDescription(request.description());
        careerPath.setRequiredSkills(request.requiredSkills());
        careerPath.setRoadmap(request.roadmap());
        careerPath.setAveragePackage(request.averagePackage());
        careerPath.setGrowthOutlook(request.growthOutlook());
        careerPath.setDemandLevel(request.demandLevel());
        careerPath.setFeatured(request.featured());
        careerPath.setStudentsExplored(request.studentsExplored());
    }

    private CareerPathResponse mapToResponse(CareerPath careerPath) {
        return new CareerPathResponse(
                careerPath.getId(),
                careerPath.getTitle(),
                careerPath.getCategory(),
                careerPath.getDescription(),
                careerPath.getRequiredSkills(),
                careerPath.getRoadmap(),
                careerPath.getAveragePackage(),
                careerPath.getGrowthOutlook(),
                careerPath.getDemandLevel(),
                careerPath.isFeatured(),
                careerPath.getStudentsExplored()
        );
    }
}
