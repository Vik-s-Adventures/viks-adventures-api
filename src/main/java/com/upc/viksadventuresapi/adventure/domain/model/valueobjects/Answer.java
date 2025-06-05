package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Answer(String answer) {
    public Answer {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be null or blank");
        }
    }
}
