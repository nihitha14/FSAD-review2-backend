package com.careerguidance.platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "career_paths")
public class CareerPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    @Column(length = 2500)
    private String description;

    @Column(length = 1500)
    private String requiredSkills;

    @Column(length = 2500)
    private String roadmap;

    private String averagePackage;

    private String growthOutlook;

    private String demandLevel;

    private boolean featured;

    private int studentsExplored;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(String roadmap) {
        this.roadmap = roadmap;
    }

    public String getAveragePackage() {
        return averagePackage;
    }

    public void setAveragePackage(String averagePackage) {
        this.averagePackage = averagePackage;
    }

    public String getGrowthOutlook() {
        return growthOutlook;
    }

    public void setGrowthOutlook(String growthOutlook) {
        this.growthOutlook = growthOutlook;
    }

    public String getDemandLevel() {
        return demandLevel;
    }

    public void setDemandLevel(String demandLevel) {
        this.demandLevel = demandLevel;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public int getStudentsExplored() {
        return studentsExplored;
    }

    public void setStudentsExplored(int studentsExplored) {
        this.studentsExplored = studentsExplored;
    }
}
