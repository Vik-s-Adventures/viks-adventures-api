package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Subtitle(String subtitle) {
    public Subtitle {
        if (subtitle == null || subtitle.isBlank()) {
            throw new IllegalArgumentException("Subtitle cannot be null or blank");
        }
        if (subtitle.length() > 255) {
            throw new IllegalArgumentException("Subtitle cannot exceed 255 characters");
        }
    }
}
