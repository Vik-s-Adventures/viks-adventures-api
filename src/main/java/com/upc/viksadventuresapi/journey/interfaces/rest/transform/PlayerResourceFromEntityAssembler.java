package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerResource;

public class PlayerResourceFromEntityAssembler {
    public static PlayerResource toResourceFromEntity(Player entity) {
        return new PlayerResource(
                entity.getId(),
                entity.getProfile().getId(),
                entity.getTotalScore()
        );
    }
}
