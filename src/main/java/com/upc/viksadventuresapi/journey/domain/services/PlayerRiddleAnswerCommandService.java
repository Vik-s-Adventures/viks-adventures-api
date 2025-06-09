package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerRiddleAnswerCommand;

import java.util.Optional;

public interface PlayerRiddleAnswerCommandService {
    Optional<PlayerRiddleAnswer> handle(CreatePlayerRiddleAnswerCommand command);
    void handle(DeletePlayerRiddleAnswerCommand command);
}
