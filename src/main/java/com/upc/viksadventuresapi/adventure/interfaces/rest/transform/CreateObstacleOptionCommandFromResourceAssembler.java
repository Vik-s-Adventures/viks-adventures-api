package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleOptionCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateObstacleOptionResource;

public class CreateObstacleOptionCommandFromResourceAssembler {
    public static CreateObstacleOptionCommand toCommandFromResource(CreateObstacleOptionResource resource){
        return new CreateObstacleOptionCommand(
                resource.obstacleId(),
                resource.optionText(),
                resource.isCorrect()
        );
    }
}
