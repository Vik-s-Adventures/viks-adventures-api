package com.upc.viksadventuresapi.quiz.domain.model.valueobjects;

public record ImageUrl(String imageUrl) {
    public ImageUrl {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("imageUrl cannot be null or empty");
        }
    }
}
