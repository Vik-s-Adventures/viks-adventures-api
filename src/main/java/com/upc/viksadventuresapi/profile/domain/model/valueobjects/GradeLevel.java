package com.upc.viksadventuresapi.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record GradeLevel(String gradeLevel) {
    public GradeLevel {
        if (gradeLevel.isBlank()) {
            throw new IllegalArgumentException("Grade level cannot be blank.");
        }
    }
}
