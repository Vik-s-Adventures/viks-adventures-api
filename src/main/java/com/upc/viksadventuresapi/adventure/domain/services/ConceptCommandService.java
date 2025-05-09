package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateConceptCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteConceptCommand;

import java.util.Optional;

public interface ConceptCommandService {
    Optional<Concept> handle(CreateConceptCommand command);
    void handle(DeleteConceptCommand command);
}
