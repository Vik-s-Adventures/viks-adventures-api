package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLinkingCommand;

import java.util.Optional;

public interface LinkingCommandService {
    Optional<Linking> handle(CreateLinkingCommand command);
    void handle(DeleteLinkingCommand command);
}
