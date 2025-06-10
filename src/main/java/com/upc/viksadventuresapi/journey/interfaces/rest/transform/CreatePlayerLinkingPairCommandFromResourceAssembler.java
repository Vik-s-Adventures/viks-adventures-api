package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerLinkingPairCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerLinkingPairResource;

public class CreatePlayerLinkingPairCommandFromResourceAssembler {
    public static CreatePlayerLinkingPairCommand toCommandFromResource(CreatePlayerLinkingPairResource resource) {
        return new CreatePlayerLinkingPairCommand(
                resource.playerProgressId(),
                resource.linkingPairImageId(),
                resource.linkingPairAnswerId()
        );
    }
}
