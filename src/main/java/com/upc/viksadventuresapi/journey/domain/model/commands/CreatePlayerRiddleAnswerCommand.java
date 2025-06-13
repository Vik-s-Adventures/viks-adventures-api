package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerRiddleAnswerCommand(
        Long playerId,
        Long riddleDetailId,
        String enteredAnswer
) {
}
