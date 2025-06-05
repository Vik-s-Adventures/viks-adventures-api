package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLinksQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinksByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkingQueryServiceImpl implements LinkingQueryService {
    private final LinkingRepository linkingRepository;

    @Override
    public Optional<Linking> handle(GetLinkingByIdQuery query) {
        return linkingRepository.findById(query.linkingId());
    }

    @Override
    public List<Linking> handle(GetLinksByTrialIdQuery query) {
        return linkingRepository.findByTrialId(query.trialId());
    }

    @Override
    public List<Linking> handle(GetAllLinksQuery query) {
        return linkingRepository.findAll();
    }
}
