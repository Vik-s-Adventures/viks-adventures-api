package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTrialCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateTrialResource;

public class CreateTrialCommandFromResourceAssembler {
    public static CreateTrialCommand toCommandFromResource(CreateTrialResource resource) {
        return new CreateTrialCommand(
                resource.levelId(),
                resource.description()
        );
    }
}
