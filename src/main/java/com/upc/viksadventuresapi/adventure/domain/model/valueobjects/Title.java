package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

public record Title(String title) {
    public Title {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (title.length() > 255) {
            throw new IllegalArgumentException("Title cannot exceed 255 characters");
        }
    }
}
