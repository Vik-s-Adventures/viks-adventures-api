package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.ObstacleOptionResource;

public class ObstacleOptionResourceFromEntityAssembler {
    public static ObstacleOptionResource toResourceFromEntity(ObstacleOption entity) {
        return new ObstacleOptionResource(
                entity.getId(),
                entity.getObstacle().getId(),
                entity.getOptionText(),
                entity.getIsCorrect()
        );
    }
}
