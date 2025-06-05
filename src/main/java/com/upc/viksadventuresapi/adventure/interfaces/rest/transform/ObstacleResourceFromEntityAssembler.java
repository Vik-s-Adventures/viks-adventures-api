package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Obstacle;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.ObstacleResource;

public class ObstacleResourceFromEntityAssembler {
    public static ObstacleResource toResourceFromEntity(Obstacle entity) {
        return new ObstacleResource(
                entity.getId(),
                entity.getFinalBattle().getId(),
                entity.getDescription().description(),
                entity.getImageUrl().imageUrl()
        );
    }
}
