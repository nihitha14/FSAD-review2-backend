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

import com.careerguidance.platform.dto.CounsellorRequest;
import com.careerguidance.platform.dto.CounsellorResponse;
import com.careerguidance.platform.service.CounsellorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/counsellors")
public class CounsellorController {

    private final CounsellorService counsellorService;

    public CounsellorController(CounsellorService counsellorService) {
        this.counsellorService = counsellorService;
    }

    @GetMapping
    public List<CounsellorResponse> getCounsellors(@RequestParam(required = false) String specialization) {
        return counsellorService.getCounsellors(specialization);
    }

    @GetMapping("/{id}")
    public CounsellorResponse getCounsellorById(@PathVariable Long id) {
        return counsellorService.getCounsellorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CounsellorResponse createCounsellor(@Valid @RequestBody CounsellorRequest request) {
        return counsellorService.createCounsellor(request);
    }

    @PutMapping("/{id}")
    public CounsellorResponse updateCounsellor(@PathVariable Long id, @Valid @RequestBody CounsellorRequest request) {
        return counsellorService.updateCounsellor(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCounsellor(@PathVariable Long id) {
        counsellorService.deleteCounsellor(id);
    }
}
