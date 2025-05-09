package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLevelsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelsByWorldIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.LevelQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelQueryServiceImpl implements LevelQueryService {
    private final LevelRepository levelRepository;

    @Override
    public Optional<Level> handle(GetLevelByIdQuery query) {
        return levelRepository.findById(query.levelId());
    }

    @Override
    public List<Level> handle(GetLevelsByWorldIdQuery query) {
        return levelRepository.findByWorldId(query.worldId());
    }

    @Override
    public List<Level> handle(GetAllLevelsQuery query) {
        return levelRepository.findAll();
    }
}
