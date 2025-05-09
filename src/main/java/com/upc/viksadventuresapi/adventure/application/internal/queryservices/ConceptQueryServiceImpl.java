package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllConceptsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetConceptByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetConceptsByTomeIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.ConceptQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ConceptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConceptQueryServiceImpl implements ConceptQueryService {
    private final ConceptRepository conceptRepository;

    @Override
    public Optional<Concept> handle(GetConceptByIdQuery query) {
        return conceptRepository.findById(query.conceptId());
    }

    @Override
    public List<Concept> handle(GetConceptsByTomeIdQuery query) {
        return conceptRepository.findByTomeId(query.tomeId());
    }

    @Override
    public List<Concept> handle(GetAllConceptsQuery query) {
        return conceptRepository.findAll();
    }
}
