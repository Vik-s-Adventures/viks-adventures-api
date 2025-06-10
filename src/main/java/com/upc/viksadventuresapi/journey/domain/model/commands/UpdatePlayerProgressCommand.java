package com.upc.viksadventuresapi.journey.domain.model.commands;

import java.time.LocalDateTime;

public record UpdatePlayerProgressCommand(
        Boolean completed,
        Integer score,
        LocalDateTime lastAccessed
) {
}