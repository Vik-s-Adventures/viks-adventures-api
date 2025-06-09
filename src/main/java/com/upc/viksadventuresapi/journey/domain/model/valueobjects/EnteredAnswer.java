package com.upc.viksadventuresapi.journey.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EnteredAnswer(String enteredAnswer) {
    public EnteredAnswer {
        if (enteredAnswer == null || enteredAnswer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be null or blank");
        }
    }
}
