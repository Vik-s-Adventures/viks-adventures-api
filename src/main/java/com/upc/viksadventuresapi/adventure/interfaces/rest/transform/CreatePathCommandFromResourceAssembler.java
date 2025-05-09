package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreatePathCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreatePathResource;

public class CreatePathCommandFromResourceAssembler {
    public static CreatePathCommand toCommandFromResource(CreatePathResource resource) {
        return new CreatePathCommand(
                resource.levelId(),
                resource.description(),
                resource.imageUrl()
        );
    }
}
