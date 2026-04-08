package com.careerguidance.platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerguidance.platform.dto.ResourceRequest;
import com.careerguidance.platform.dto.ResourceResponse;
import com.careerguidance.platform.exception.ResourceNotFoundException;
import com.careerguidance.platform.model.CareerPath;
import com.careerguidance.platform.model.CareerResource;
import com.careerguidance.platform.repository.CareerResourceRepository;

@Service
public class CareerResourceService {

    private final CareerResourceRepository careerResourceRepository;
    private final CareerPathService careerPathService;

    public CareerResourceService(CareerResourceRepository careerResourceRepository, CareerPathService careerPathService) {
        this.careerResourceRepository = careerResourceRepository;
        this.careerPathService = careerPathService;
    }

    public List<ResourceResponse> getResources(String type, Long careerPathId) {
        List<CareerResource> resources;

        if (careerPathId != null) {
            resources = careerResourceRepository.findByCareerPath_Id(careerPathId);
        } else if (type != null && !type.isBlank()) {
            resources = careerResourceRepository.findByTypeIgnoreCase(type);
        } else {
            resources = careerResourceRepository.findAll();
        }

        return resources.stream().map(this::mapToResponse).toList();
    }

    public ResourceResponse getResourceById(Long id) {
        return mapToResponse(getResourceEntity(id));
    }

    public ResourceResponse createResource(ResourceRequest request) {
        CareerResource resource = new CareerResource();
        applyRequest(resource, request);
        return mapToResponse(careerResourceRepository.save(resource));
    }

    public ResourceResponse updateResource(Long id, ResourceRequest request) {
        CareerResource resource = getResourceEntity(id);
        applyRequest(resource, request);
        return mapToResponse(careerResourceRepository.save(resource));
    }

    public void deleteResource(Long id) {
        careerResourceRepository.delete(getResourceEntity(id));
    }

    public CareerResource getResourceEntity(Long id) {
        return careerResourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id " + id));
    }

    private void applyRequest(CareerResource resource, ResourceRequest request) {
        CareerPath careerPath = careerPathService.getCareerPathEntity(request.careerPathId());
        resource.setTitle(request.title());
        resource.setType(request.type());
        resource.setDescription(request.description());
        resource.setUrl(request.url());
        resource.setDuration(request.duration());
        resource.setLevel(request.level());
        resource.setCareerPath(careerPath);
    }

    private ResourceResponse mapToResponse(CareerResource resource) {
        return new ResourceResponse(
                resource.getId(),
                resource.getTitle(),
                resource.getType(),
                resource.getDescription(),
                resource.getUrl(),
                resource.getDuration(),
                resource.getLevel(),
                resource.getCareerPath().getId(),
                resource.getCareerPath().getTitle()
        );
    }
}
