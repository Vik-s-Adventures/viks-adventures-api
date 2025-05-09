package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

import com.upc.viksadventuresapi.adventure.domain.model.enums.CompetenceType;

public record WorldResource(
        Long id,
        String name,
        CompetenceType competenceType
) {
}
