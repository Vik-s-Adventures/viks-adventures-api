package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerProgressResource;

public class PlayerProgressResourceFromEntityAssembler {

    public static PlayerProgressResource toResourceFromEntity(PlayerProgress entity) {
        return new PlayerProgressResource(
                entity.getId(),
                entity.getPlayer().getId(),
                entity.getLevel().getId(),
                entity.getCompleted(),
                entity.getScore(),
                entity.getLastAccessed()
        );
    }
}
