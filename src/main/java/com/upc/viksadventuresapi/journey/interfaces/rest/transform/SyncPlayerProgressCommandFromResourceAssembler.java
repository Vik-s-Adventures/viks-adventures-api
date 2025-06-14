package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.SyncPlayerProgressCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.SyncPlayerProgressResource;

public class SyncPlayerProgressCommandFromResourceAssembler {
    public static SyncPlayerProgressCommand toCommandFromResource(SyncPlayerProgressResource resource) {
        return new SyncPlayerProgressCommand(
                resource.playerId()
        );
    }
}