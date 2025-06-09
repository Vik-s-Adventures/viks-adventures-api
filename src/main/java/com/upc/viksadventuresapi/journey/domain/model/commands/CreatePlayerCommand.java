package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerCommand(
        Long profileId,
        Integer totalScore
) {
}
