package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerProgressResource;

public class CreatePlayerProgressCommandFromResourceAssembler {
    public static CreatePlayerProgressCommand toCommandFromResource(CreatePlayerProgressResource resource) {
        return new CreatePlayerProgressCommand(
                resource.playerId(),
                resource.levelId(),
                resource.completed(),
                resource.score(),
                resource.lastAccessed()
        );
    }
}
