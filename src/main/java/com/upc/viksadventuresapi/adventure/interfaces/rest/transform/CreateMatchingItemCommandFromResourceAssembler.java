package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingItemCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateMatchingItemResource;

public class CreateMatchingItemCommandFromResourceAssembler {
    public static CreateMatchingItemCommand toCommandFromResource(CreateMatchingItemResource resource) {
        return new CreateMatchingItemCommand(
                resource.matchingPairId(),
                resource.imageUrl(),
                resource.isDistractor()
        );
    }
}
