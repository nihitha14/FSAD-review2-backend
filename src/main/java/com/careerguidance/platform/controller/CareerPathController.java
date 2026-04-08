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

import com.careerguidance.platform.dto.CareerPathRequest;
import com.careerguidance.platform.dto.CareerPathResponse;
import com.careerguidance.platform.service.CareerPathService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/career-paths")
public class CareerPathController {

    private final CareerPathService careerPathService;

    public CareerPathController(CareerPathService careerPathService) {
        this.careerPathService = careerPathService;
    }

    @GetMapping
    public List<CareerPathResponse> getCareerPaths(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean featured
    ) {
        return careerPathService.getCareerPaths(keyword, featured);
    }

    @GetMapping("/{id}")
    public CareerPathResponse getCareerPathById(@PathVariable Long id) {
        return careerPathService.getCareerPathById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CareerPathResponse createCareerPath(@Valid @RequestBody CareerPathRequest request) {
        return careerPathService.createCareerPath(request);
    }

    @PutMapping("/{id}")
    public CareerPathResponse updateCareerPath(@PathVariable Long id, @Valid @RequestBody CareerPathRequest request) {
        return careerPathService.updateCareerPath(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCareerPath(@PathVariable Long id) {
        careerPathService.deleteCareerPath(id);
    }
}
