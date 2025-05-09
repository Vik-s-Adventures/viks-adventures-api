package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Advice(String advice) {
    public Advice {
        if (advice == null || advice.isBlank()) {
            throw new IllegalArgumentException("Advice cannot be null or blank");
        }
        if (advice.length() > 255) {
            throw new IllegalArgumentException("Advice cannot exceed 255 characters");
        }
    }
}
