package com.careerguidance.platform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.careerguidance.platform.dto.SessionRequest;
import com.careerguidance.platform.dto.SessionResponse;
import com.careerguidance.platform.dto.SessionStatusUpdateRequest;
import com.careerguidance.platform.model.SessionStatus;
import com.careerguidance.platform.service.SessionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<SessionResponse> getSessions(@RequestParam(required = false) SessionStatus status) {
        return sessionService.getSessions(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponse createSession(@Valid @RequestBody SessionRequest request) {
        return sessionService.createSession(request);
    }

    @PatchMapping("/{id}/status")
    public SessionResponse updateSessionStatus(
            @PathVariable Long id,
            @Valid @RequestBody SessionStatusUpdateRequest request
    ) {
        return sessionService.updateSessionStatus(id, request);
    }
}
