package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreatePlayerProgressResource(
        Long playerId,
        Long levelId,
        Boolean completed,
        Integer score,
        LocalDateTime lastAccessed
) {
}
