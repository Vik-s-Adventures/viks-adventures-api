package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllRiddlesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddlesByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.RiddleQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.RiddleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiddleQueryServiceImpl implements RiddleQueryService {
    private final RiddleRepository riddleRepository;

    @Override
    public Optional<Riddle> handle(GetRiddleByIdQuery query) {
        return riddleRepository.findById(query.riddleId());
    }

    @Override
    public List<Riddle> handle(GetRiddlesByTrialIdQuery query) {
        return riddleRepository.findByTrialId(query.trialId());
    }

    @Override
    public List<Riddle> handle(GetAllRiddlesQuery query) {
        return riddleRepository.findAll();
    }
}
