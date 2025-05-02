package com.upc.viksadventuresapi.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public record Sex(String sex) {
    public Sex {
        if (!List.of("Masculino", "Femenino", "Otro").contains(sex)) {
            throw new IllegalArgumentException("Invalid sex value.");
        }
    }
}
