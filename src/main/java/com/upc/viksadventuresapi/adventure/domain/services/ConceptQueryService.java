package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllConceptsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetConceptByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetConceptsByTomeIdQuery;

import java.util.List;
import java.util.Optional;

public interface ConceptQueryService {
    Optional<Concept> handle(GetConceptByIdQuery query);
    List<Concept> handle(GetConceptsByTomeIdQuery query);
    List<Concept> handle(GetAllConceptsQuery query);
}
