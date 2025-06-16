package com.upc.viksadventuresapi.adventure.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public record ImageUrl(String imageUrl) {
    private static final Pattern URL_PATTERN =
            Pattern.compile("^(https?://.*\\.(?:jpg|jpeg|png|gif))$", Pattern.CASE_INSENSITIVE);

    public ImageUrl {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("La URL de la imagen no puede ser nula o vacía.");
        }
        if (!URL_PATTERN.matcher(imageUrl).matches()) {
            throw new IllegalArgumentException("La URL proporcionada no tiene un formato válido.");
        }
    }
}