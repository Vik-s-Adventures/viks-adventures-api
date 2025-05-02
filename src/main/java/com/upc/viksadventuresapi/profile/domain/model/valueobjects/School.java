package com.upc.viksadventuresapi.profile.domain.model.valueobjects;

public record School(String school) {
    public School {
        if (school == null || school.isBlank()) {
            throw new IllegalArgumentException("School cannot be null or blank");
        }
    }
}
