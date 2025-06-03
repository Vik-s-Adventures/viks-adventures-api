package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteRiddleCommand;

import java.util.Optional;

public interface TrialRiddleCommandService {
    Optional<Riddle> handle(CreateRiddleCommand command);
    void handle(DeleteRiddleCommand command);
}
