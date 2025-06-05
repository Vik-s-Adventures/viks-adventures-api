package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateLinkingResource;

public class CreateLinkingCommandFromResourceAssembler {
    public static CreateLinkingCommand toCommandFromResource(CreateLinkingResource resource) {
        return new CreateLinkingCommand(
                resource.trialId(),
                resource.description()
        );
    }
}
