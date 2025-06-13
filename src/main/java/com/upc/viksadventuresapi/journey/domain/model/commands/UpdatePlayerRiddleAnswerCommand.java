package com.upc.viksadventuresapi.journey.domain.model.commands;

public record UpdatePlayerRiddleAnswerCommand(
        Long playerId,
        Long riddleDetailId,
        String enteredAnswer
) {
}
