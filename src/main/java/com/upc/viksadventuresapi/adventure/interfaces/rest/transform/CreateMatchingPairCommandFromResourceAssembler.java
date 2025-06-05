package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingPairCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateMatchingPairResource;

public class CreateMatchingPairCommandFromResourceAssembler {
    public static CreateMatchingPairCommand toCommandFromResource(CreateMatchingPairResource resource) {
        return new CreateMatchingPairCommand(
                resource.matchingId()
        );
    }
}
