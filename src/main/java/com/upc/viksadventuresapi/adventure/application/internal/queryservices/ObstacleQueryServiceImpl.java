package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Obstacle;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllObstaclesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstaclesByFinalBattleIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ObstacleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObstacleQueryServiceImpl implements ObstacleQueryService {
    private final ObstacleRepository obstacleRepository;

    @Override
    public Optional<Obstacle> handle(GetObstacleByIdQuery query) {
        return obstacleRepository.findById(query.obstacleId());
    }

    @Override
    public List<Obstacle> handle(GetObstaclesByFinalBattleIdQuery query) {
        return obstacleRepository.findByFinalBattleId(query.finalBattleId());
    }

    @Override
    public List<Obstacle> handle(GetAllObstaclesQuery query) {
        return obstacleRepository.findAll();
    }
}
