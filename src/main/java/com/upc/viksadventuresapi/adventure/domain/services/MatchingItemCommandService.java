package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingItemCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingItemCommand;

import java.util.Optional;

public interface MatchingItemCommandService {
    Optional<MatchingItem> handle(CreateMatchingItemCommand command);
    void handle(DeleteMatchingItemCommand command);
}