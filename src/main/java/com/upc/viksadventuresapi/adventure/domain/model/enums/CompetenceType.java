package com.upc.viksadventuresapi.adventure.domain.model.enums;

import lombok.Getter;

@Getter
public enum CompetenceType {
    QUANTITY("Resuelve problemas de cantidad"),
    EQUIVALENCE("Resuelve problemas de regularidad, equivalencia y cambio"),
    SHAPE("Resuelve problemas de forma, movimiento y localización"),
    DATA("Resuelve problemas de gestión de datos e incertidumbre");

    private final String description;

    CompetenceType(String description) {
        this.description = description;
    }
}
