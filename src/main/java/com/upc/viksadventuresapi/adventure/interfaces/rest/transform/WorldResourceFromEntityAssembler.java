package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.WorldResource;

public class WorldResourceFromEntityAssembler {
    public static WorldResource toResourceFromEntity(World entity) {
        return new WorldResource(
                entity.getId(),
                entity.getName().name(),
                entity.getCompetenceType()
        );
    }
}
