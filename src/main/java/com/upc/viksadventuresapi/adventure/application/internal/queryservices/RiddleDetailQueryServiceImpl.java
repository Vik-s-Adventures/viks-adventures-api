package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllRiddlesDetailQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleDetailByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleDetailsByRiddleIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.RiddleDetailQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.RiddleDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiddleDetailQueryServiceImpl implements RiddleDetailQueryService {
    private final RiddleDetailRepository riddleDetailRepository;

    @Override
    public Optional<RiddleDetail> handle(GetRiddleDetailByIdQuery query) {
        return riddleDetailRepository.findById(query.riddleDetailId());
    }

    @Override
    public List<RiddleDetail> handle(GetRiddleDetailsByRiddleIdQuery query) {
        return riddleDetailRepository.findByRiddleId(query.riddleId());
    }

    @Override
    public List<RiddleDetail> handle(GetAllRiddlesDetailQuery query) {
        return riddleDetailRepository.findAll();
    }
}
