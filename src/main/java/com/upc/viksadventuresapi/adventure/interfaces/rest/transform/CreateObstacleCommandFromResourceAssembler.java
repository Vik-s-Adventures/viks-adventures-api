package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateObstacleResource;

public class CreateObstacleCommandFromResourceAssembler {
    public static CreateObstacleCommand toCommandFromResource(CreateObstacleResource resource) {
        return new CreateObstacleCommand(
                resource.finalBattleId(),
                resource.imageUrl(),
                resource.description()
        );
    }
}
