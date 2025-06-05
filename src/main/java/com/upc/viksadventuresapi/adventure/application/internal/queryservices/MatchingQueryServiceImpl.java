package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllMatchesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchesByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingQueryServiceImpl implements MatchingQueryService {
    private final MatchingRepository matchingRepository;

    @Override
    public Optional<Matching> handle(GetMatchingByIdQuery query) {
        return matchingRepository.findById(query.matchingId());
    }

    @Override
    public List<Matching> handle(GetMatchesByTrialIdQuery query) {
        return matchingRepository.findByTrialId(query.trialId());
    }

    @Override
    public List<Matching> handle(GetAllMatchesQuery query) {
        return matchingRepository.findAll();
    }
}
