package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Welcome(String welcome) {
    public Welcome {
        if (welcome == null || welcome.isBlank()) {
            throw new IllegalArgumentException("Welcome cannot be null or blank");
        }
        if (welcome.length() > 255) {
            throw new IllegalArgumentException("Welcome cannot exceed 255 characters");
        }
    }
}
