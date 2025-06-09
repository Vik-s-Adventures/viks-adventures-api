package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerResource;

public class CreatePlayerCommandFromResourceAssembler {
    public static CreatePlayerCommand toCommandFromResource(CreatePlayerResource resource) {
        return new CreatePlayerCommand(
                resource.profileId(),
                resource.totalScore()
        );
    }
}
