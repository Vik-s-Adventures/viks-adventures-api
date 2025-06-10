package com.upc.viksadventuresapi.journey.application.internal.queryservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerLinkingPairByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerLinkingPairQueryService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerLinkingPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerLinkingPairQueryServiceImpl implements PlayerLinkingPairQueryService {
    private final PlayerLinkingPairRepository playerLinkingPairRepository;

    @Override
    public Optional<PlayerLinkingPair> handle(GetPlayerLinkingPairByIdQuery query) {
        return playerLinkingPairRepository.findById(query.playerLinkingPairId());
    }
}