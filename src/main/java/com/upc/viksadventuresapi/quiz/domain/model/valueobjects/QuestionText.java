package com.upc.viksadventuresapi.quiz.domain.model.valueobjects;

public record QuestionText(String questionText) {
    public QuestionText {
        if (questionText == null || questionText.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be null or empty");
        }
    }
}
