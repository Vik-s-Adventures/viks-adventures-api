package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

public record Name (String name){
    public Name {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot exceed 50 characters");
        }
    }
}
