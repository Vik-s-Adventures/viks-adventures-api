package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerFinalBattleResource;

public class PlayerFinalBattleResourceFromEntityAssembler {
    public static PlayerFinalBattleResource toResourceFromEntity(PlayerFinalBattle entity) {
        return new PlayerFinalBattleResource(
                entity.getId(),
                entity.getPlayer().getId(),
                entity.getObstacleOption().getId()
        );
    }
}
