package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Performance(String performance) {
    public Performance {
        if (performance == null || performance.isBlank()) {
            throw new IllegalArgumentException("Performance cannot be null or blank");
        }
        if (performance.length() > 255) {
            throw new IllegalArgumentException("Performance cannot exceed 255 characters");
        }
    }
}
