package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerTomesReviewedResource;

public class CreatePlayerTomesReviewedCommandFromResourceAssembler {
    public static CreatePlayerTomesReviewedCommand toCommandFromResource(CreatePlayerTomesReviewedResource resource) {
        return new CreatePlayerTomesReviewedCommand(
                resource.playerId(),
                resource.conceptId()
        );
    }
}
