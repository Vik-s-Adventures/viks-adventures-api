package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTrialCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTrialCommand;
import com.upc.viksadventuresapi.adventure.domain.services.TrialCommandService;

import java.util.Optional;

public class TrialCommandServiceImpl implements TrialCommandService {
    @Override
    public Optional<Trial> handle(CreateTrialCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteTrialCommand command) {

    }
}
