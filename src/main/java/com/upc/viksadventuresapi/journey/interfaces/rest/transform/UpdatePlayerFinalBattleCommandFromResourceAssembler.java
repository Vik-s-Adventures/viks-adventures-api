package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerFinalBattleResource;

public class UpdatePlayerFinalBattleCommandFromResourceAssembler {
    public static UpdatePlayerFinalBattleCommand toCommandFromResource(UpdatePlayerFinalBattleResource resource) {
        return new UpdatePlayerFinalBattleCommand(
                resource.playerId(),
                resource.obstacleId(),
                resource.newObstacleOptionId()
        );
    }
}
