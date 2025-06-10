package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerResource;

public class UpdatePlayerCommandFromResourceAssembler {
    public static UpdatePlayerCommand toCommandFromResource(UpdatePlayerResource resource) {
        return new UpdatePlayerCommand(
                resource.totalScore()
        );
    }
}
