package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleDetailCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateRiddleDetailResource;

public class CreateRiddleDetailCommandFromResourceAssembler {
    public static CreateRiddleDetailCommand toCommandFromResource(CreateRiddleDetailResource resource) {
        return new CreateRiddleDetailCommand(
                resource.riddleId(),
                resource.imageUrl(),
                resource.answer()
        );
    }
}
