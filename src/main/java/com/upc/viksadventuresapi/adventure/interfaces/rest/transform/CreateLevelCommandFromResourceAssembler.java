package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLevelCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateLevelResource;

public class CreateLevelCommandFromResourceAssembler {
    public static CreateLevelCommand toCommandFromResource(CreateLevelResource resource) {
        return new CreateLevelCommand(
                resource.worldId(),
                resource.name(),
                resource.performance()
        );
    }
}
