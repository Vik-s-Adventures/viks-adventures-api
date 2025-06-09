package com.upc.viksadventuresapi.journey.application.internal.queryservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerMatchingPairByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerMatchingPairQueryService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerMatchingPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerMatchingPairQueryServiceImpl implements PlayerMatchingPairQueryService {
    private final PlayerMatchingPairRepository playerMatchingPairRepository;

    @Override
    public Optional<PlayerMatchingPair> handle(GetPlayerMatchingPairByIdQuery query) {
        return playerMatchingPairRepository.findById(query.playerMatchingPairId());
    }
}
