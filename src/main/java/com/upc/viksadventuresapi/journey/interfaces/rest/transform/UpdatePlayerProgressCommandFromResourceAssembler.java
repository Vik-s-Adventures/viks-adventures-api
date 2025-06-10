package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerProgressResource;

public class UpdatePlayerProgressCommandFromResourceAssembler {
    public static UpdatePlayerProgressCommand toCommandFromResource(UpdatePlayerProgressResource resource) {
        return new UpdatePlayerProgressCommand(
                resource.completed(),
                resource.score(),
                resource.lastAccessed()
        );
    }
}
