package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerRiddleAnswerCommand(
        Long playerProgressId,
        Long riddleDetailId,
        String enteredAnswer
) {
}
