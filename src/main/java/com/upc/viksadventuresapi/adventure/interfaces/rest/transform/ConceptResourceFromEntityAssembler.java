package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.ConceptResource;

public class ConceptResourceFromEntityAssembler {
    public static ConceptResource toResourceFromEntity(Concept entity) {
        return new ConceptResource(
                entity.getId(),
                entity.getTome().getId(),
                entity.getSubtitle().subtitle(),
                entity.getDescription().description(),
                entity.getImageUrl().imageUrl()
        );
    }
}