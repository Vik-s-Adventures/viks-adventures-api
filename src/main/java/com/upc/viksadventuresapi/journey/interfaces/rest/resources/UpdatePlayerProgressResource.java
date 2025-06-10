package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdatePlayerProgressResource(
        Boolean completed,
        Integer score,
        LocalDateTime lastAccessed
) {
}
