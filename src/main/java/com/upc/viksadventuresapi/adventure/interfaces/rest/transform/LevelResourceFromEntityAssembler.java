package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.LevelResource;

public class LevelResourceFromEntityAssembler {
    public static LevelResource toResourceFromEntity(Level entity) {
        return new LevelResource(
                entity.getId(),
                entity.getWorld().getId(),
                entity.getName().name(),
                entity.getPerformance().performance()
        );
    }
}
