package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingPairCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateLinkingPairResource;

public class CreateLinkingPairCommandFromResourceAssembler {
    public static CreateLinkingPairCommand toCommandFromResource(CreateLinkingPairResource resource) {
        return new CreateLinkingPairCommand(
                resource.linkinId(),
                resource.imageUrl(),
                resource.answer()
        );
    }
}