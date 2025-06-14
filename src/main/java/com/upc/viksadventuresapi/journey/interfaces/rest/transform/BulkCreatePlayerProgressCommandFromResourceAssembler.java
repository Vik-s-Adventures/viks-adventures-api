package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.BulkCreatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.BulkCreatePlayerProgressResource;

public class BulkCreatePlayerProgressCommandFromResourceAssembler {
    public static BulkCreatePlayerProgressCommand toCommandFromResource(BulkCreatePlayerProgressResource resource) {
        return new BulkCreatePlayerProgressCommand(
                resource.playerId(),
                resource.worldId()
        );
    }
}