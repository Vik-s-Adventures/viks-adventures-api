package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.MatchingResource;

public class MatchingResourceFromEntityAssembler {
    public static MatchingResource toResourceFromEntity(Matching entity) {
        return new MatchingResource(
                entity.getId(),
                entity.getTrial().getId(),
                entity.getDescription().description()
        );
    }
}
