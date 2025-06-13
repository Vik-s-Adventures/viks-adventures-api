package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerTomesReviewedResource;

public class UpdatePlayerTomesReviewedCommandFromResourceAssembler {
    public static UpdatePlayerTomesReviewedCommand toCommandFromResource(UpdatePlayerTomesReviewedResource resource) {
        return new UpdatePlayerTomesReviewedCommand(
                resource.playerId(),
                resource.conceptId()
        );
    }
}
