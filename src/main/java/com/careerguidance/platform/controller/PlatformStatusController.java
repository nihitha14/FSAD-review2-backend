package com.careerguidance.platform.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlatformStatusController {

    @GetMapping("/")
    public Map<String, String> rootStatus() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("application", "CareerCompass Backend");
        response.put("status", "Running");
        response.put("platform", "Spring Boot REST API");
        response.put("careersEndpoint", "/api/career-paths");
        response.put("dashboardEndpoint", "/api/dashboard/stats");
        return response;
    }

    @GetMapping("/api/health")
    public Map<String, String> health() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("service", "CareerCompass Backend API");
        return response;
    }
}
