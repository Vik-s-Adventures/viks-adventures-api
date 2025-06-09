package com.upc.viksadventuresapi.journey.domain.model.commands;

import java.time.LocalDateTime;

public record CreatePlayerProgressCommand(
        Long playerId,
        Long levelId,
        Boolean completed,
        Integer score,
        LocalDateTime lastAccessed
) {
}
