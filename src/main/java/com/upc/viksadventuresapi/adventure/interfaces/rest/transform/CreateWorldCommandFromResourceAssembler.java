package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateWorldCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateWorldResource;

public class CreateWorldCommandFromResourceAssembler {
    public static CreateWorldCommand toCommandFromResource(CreateWorldResource resource) {
        return new CreateWorldCommand(
                resource.name(),
                resource.CompetenceType()
        );
    }
}
