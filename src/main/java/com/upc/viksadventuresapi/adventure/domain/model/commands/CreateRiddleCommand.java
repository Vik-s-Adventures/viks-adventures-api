package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateRiddleCommand(
        Long trialId,
        String question
) {
}
