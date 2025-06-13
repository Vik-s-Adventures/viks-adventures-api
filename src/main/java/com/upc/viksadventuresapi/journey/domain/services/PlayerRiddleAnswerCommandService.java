package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerRiddleAnswerCommand;

import java.util.Optional;

public interface PlayerRiddleAnswerCommandService {
    Optional<PlayerRiddleAnswer> handle(CreatePlayerRiddleAnswerCommand command);
    Optional<PlayerRiddleAnswer> handle(UpdatePlayerRiddleAnswerCommand command);
    void handle(DeletePlayerRiddleAnswerCommand command);
}