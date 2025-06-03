package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Question(String question) {
    public Question {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be null or blank");
        }
    }
}
