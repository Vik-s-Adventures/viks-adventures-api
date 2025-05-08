package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

public record Description(String description) {
    public Description {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (description.length() > 255) {
            throw new IllegalArgumentException("Description cannot exceed 255 characters");
        }
    }
}
