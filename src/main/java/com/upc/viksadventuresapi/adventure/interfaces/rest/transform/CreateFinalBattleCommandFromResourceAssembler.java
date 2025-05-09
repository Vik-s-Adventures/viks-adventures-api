package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateFinalBattleCommand;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateFinalBattleResource;

public class CreateFinalBattleCommandFromResourceAssembler {
    public static CreateFinalBattleCommand toCommandFromResource(CreateFinalBattleResource resource) {
        return new CreateFinalBattleCommand(
                resource.levelId(),
                resource.description()
        );
    }
}