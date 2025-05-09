package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.TrialResource;

public class TrialResourceFromEntityAssembler {
    public static TrialResource toResourceFromEntity(Trial entity) {
        return new TrialResource(
                entity.getId(),
                entity.getLevel().getId(),
                entity.getDescription().description()
        );
    }
}