package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerFinalBattleResource;

public class CreatePlayerFinalBattleCommandFromResourceAssembler {
    public static CreatePlayerFinalBattleCommand toCommandFromResource(CreatePlayerFinalBattleResource resource) {
        return new CreatePlayerFinalBattleCommand(
                resource.playerId(),
                resource.obstacleOptionId()
        );
    }
}
