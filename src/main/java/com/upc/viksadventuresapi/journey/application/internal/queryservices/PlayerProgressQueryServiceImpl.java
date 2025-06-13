package com.upc.viksadventuresapi.journey.application.internal.queryservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayerProgressesByLevelIdQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayerProgressesByPlayerIdAndLevelIdQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerProgressByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressQueryService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerProgressQueryServiceImpl implements PlayerProgressQueryService {
    private final PlayerProgressRepository playerProgressRepository;

    @Override
    public Optional<PlayerProgress> handle(GetPlayerProgressByIdQuery query) {
        return playerProgressRepository.findById(query.playerProgressId());
    }

    @Override
    public List<PlayerProgress> handle(GetAllPlayerProgressesByLevelIdQuery query) {
        return playerProgressRepository.findAllByLevelId(query.levelId());
    }

    @Override
    public List<PlayerProgress> handle(GetAllPlayerProgressesByPlayerIdAndLevelIdQuery query) {
        return playerProgressRepository.findAllByPlayerIdAndLevelId(query.playerId(), query.levelId());
    }
}
