package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateRiddleResource;

public class CreateRiddleCommandFromResourceAssembler {
    public static CreateRiddleCommand toCommandFromResource(CreateRiddleResource resource) {
        return new CreateRiddleCommand(
                resource.trialId(),
                resource.question()
        );
    }
}
