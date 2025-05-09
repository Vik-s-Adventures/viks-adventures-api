package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTrialCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTrialCommand;

import java.util.Optional;

public interface TrialCommandService {
    Optional<Trial> handle(CreateTrialCommand command);
    void handle(DeleteTrialCommand command);
}
