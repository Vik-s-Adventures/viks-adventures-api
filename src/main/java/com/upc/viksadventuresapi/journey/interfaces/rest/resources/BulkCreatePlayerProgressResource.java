package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record BulkCreatePlayerProgressResource(
    Long playerId,
    Long worldId
) { }