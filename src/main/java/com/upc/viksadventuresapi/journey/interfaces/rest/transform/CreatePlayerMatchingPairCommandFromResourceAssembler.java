package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerMatchingPairCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerMatchingPairResource;

public class CreatePlayerMatchingPairCommandFromResourceAssembler {
    public static CreatePlayerMatchingPairCommand toCommandFromResource(CreatePlayerMatchingPairResource resource) {
        return new CreatePlayerMatchingPairCommand(
                resource.playerProgressId(),
                resource.matchingItemA(),
                resource.matchingItemB(),
                resource.isMatched()
        );
    }
}
