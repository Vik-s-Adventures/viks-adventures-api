package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTrialsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialsByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.TrialQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TrialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrialQueryServiceImpl implements TrialQueryService {
    private final TrialRepository repository;

    @Override
    public Optional<Trial> handle(GetTrialByIdQuery query) {
        return repository.findById(query.trialId());
    }

    @Override
    public List<Trial> handle(GetTrialsByLevelIdQuery query) {
        return repository.findByLevelId(query.levelId());
    }

    @Override
    public List<Trial> handle(GetAllTrialsQuery query) {
        return repository.findAll();
    }
}
