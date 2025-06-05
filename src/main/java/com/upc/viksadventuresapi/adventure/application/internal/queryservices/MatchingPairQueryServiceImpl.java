package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingPair;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllMatchingPairsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingPairByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingPairsByMatchingIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingPairQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingPairQueryServiceImpl implements MatchingPairQueryService {
    private final MatchingPairRepository matchingPairRepository;

    @Override
    public Optional<MatchingPair> handle(GetMatchingPairByIdQuery query) {
        return matchingPairRepository.findById(query.matchingPairId());
    }

    @Override
    public List<MatchingPair> handle(GetMatchingPairsByMatchingIdQuery query) {
        return matchingPairRepository.findByMatchingId(query.matchingId());
    }

    @Override
    public List<MatchingPair> handle(GetAllMatchingPairsQuery query) {
        return  matchingPairRepository.findAll();
    }
}
