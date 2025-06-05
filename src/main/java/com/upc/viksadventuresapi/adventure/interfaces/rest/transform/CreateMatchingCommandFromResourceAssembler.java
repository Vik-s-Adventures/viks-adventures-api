package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateMatchingResource;

public class CreateMatchingCommandFromResourceAssembler {
    public static CreateMatchingCommand toCommandFromResource(CreateMatchingResource resource) {
        return new CreateMatchingCommand(
                resource.trialId(),
                resource.description()
        );
    }
}
