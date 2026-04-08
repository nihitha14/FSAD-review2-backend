package com.careerguidance.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerguidance.platform.model.CareerResource;

public interface CareerResourceRepository extends JpaRepository<CareerResource, Long> {

    List<CareerResource> findByCareerPath_Id(Long careerPathId);

    List<CareerResource> findByTypeIgnoreCase(String type);
}
