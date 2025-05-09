package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateConceptCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateConceptResource;

public class CreateConceptCommandFromResourceAssembler {
    public static CreateConceptCommand toCommandFromResource(CreateConceptResource resource) {
        return new CreateConceptCommand(
                resource.tomeId(),
                resource.subtitle(),
                resource.description(),
                resource.imageUrl()
        );
    }
}