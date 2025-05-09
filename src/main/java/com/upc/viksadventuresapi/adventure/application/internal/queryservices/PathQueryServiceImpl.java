package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Path;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllPathsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathsByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.PathQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.PathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PathQueryServiceImpl implements PathQueryService {
    private final PathRepository pathRepository;

    @Override
    public Optional<Path> handle(GetPathByIdQuery query) {
        return pathRepository.findById(query.pathId());
    }

    @Override
    public List<Path> handle(GetPathsByLevelIdQuery query) {
        return pathRepository.findByLevelId(query.levelId());
    }

    @Override
    public List<Path> handle(GetAllPathsQuery query) {
        return pathRepository.findAll();
    }
}
