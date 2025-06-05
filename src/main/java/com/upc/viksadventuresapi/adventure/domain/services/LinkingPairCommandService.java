package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLinkingPairCommand;

import java.util.Optional;

public interface LinkingPairCommandService {
    Optional<LinkingPair> handle(CreateLinkingPairCommand command);
    void handle(DeleteLinkingPairCommand command);
}
