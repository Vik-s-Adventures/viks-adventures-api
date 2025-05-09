package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTomeCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateTomeResource;

public class CreateTomeCommandFromResourceAssembler {
    public static CreateTomeCommand toCommandFromResource(CreateTomeResource resource) {
        return new CreateTomeCommand(
                resource.levelId(),
                resource.title(),
                resource.welcome(),
                resource.advice()
        );
    }
}
