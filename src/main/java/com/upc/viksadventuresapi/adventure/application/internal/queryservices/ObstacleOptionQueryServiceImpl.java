package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllObstacleOptionsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleOptionByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleOptionsByObstacleIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleOptionQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ObstacleOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObstacleOptionQueryServiceImpl implements ObstacleOptionQueryService {
    private final ObstacleOptionRepository obstacleOptionRepository;

    @Override
    public Optional<ObstacleOption> handle(GetObstacleOptionByIdQuery query) {
        return obstacleOptionRepository.findById(query.obstacleOptionId());
    }

    @Override
    public List<ObstacleOption> handle(GetObstacleOptionsByObstacleIdQuery query) {
        return obstacleOptionRepository.findByObstacleId(query.obstacleId());
    }

    @Override
    public List<ObstacleOption> handle(GetAllObstacleOptionsQuery query) {
        return obstacleOptionRepository.findAll();
    }
}
