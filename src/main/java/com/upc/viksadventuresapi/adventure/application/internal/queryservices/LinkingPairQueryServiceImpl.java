package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLinkingPairsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingPairByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingPairsByLinkingIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingPairQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkingPairQueryServiceImpl implements LinkingPairQueryService {
    private final LinkingPairRepository linkingPairRepository;

    @Override
    public Optional<LinkingPair> handle(GetLinkingPairByIdQuery query) {
        return linkingPairRepository.findById(query.linkingPairId());
    }

    @Override
    public List<LinkingPair> handle(GetLinkingPairsByLinkingIdQuery query) {
        return linkingPairRepository.findByLinkingId(query.linkingId());
    }

    @Override
    public List<LinkingPair> handle(GetAllLinkingPairsQuery query) {
        return linkingPairRepository.findAll();
    }
}
