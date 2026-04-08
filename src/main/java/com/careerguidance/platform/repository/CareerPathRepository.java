package com.careerguidance.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerguidance.platform.model.CareerPath;

public interface CareerPathRepository extends JpaRepository<CareerPath, Long> {

    List<CareerPath> findByFeaturedTrue();

    List<CareerPath> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title,
            String category,
            String description
    );
}
