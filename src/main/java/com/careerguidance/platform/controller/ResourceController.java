package com.careerguidance.platform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.careerguidance.platform.dto.ResourceRequest;
import com.careerguidance.platform.dto.ResourceResponse;
import com.careerguidance.platform.service.CareerResourceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final CareerResourceService careerResourceService;

    public ResourceController(CareerResourceService careerResourceService) {
        this.careerResourceService = careerResourceService;
    }

    @GetMapping
    public List<ResourceResponse> getResources(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long careerPathId
    ) {
        return careerResourceService.getResources(type, careerPathId);
    }

    @GetMapping("/{id}")
    public ResourceResponse getResourceById(@PathVariable Long id) {
        return careerResourceService.getResourceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceResponse createResource(@Valid @RequestBody ResourceRequest request) {
        return careerResourceService.createResource(request);
    }

    @PutMapping("/{id}")
    public ResourceResponse updateResource(@PathVariable Long id, @Valid @RequestBody ResourceRequest request) {
        return careerResourceService.updateResource(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable Long id) {
        careerResourceService.deleteResource(id);
    }
}
