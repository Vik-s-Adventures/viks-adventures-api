package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerLinkingPairRepository extends JpaRepository<PlayerLinkingPair, Long> {
    List<PlayerLinkingPair> findAllByPlayerIdAndLinkingPairAnswerLinkingId(Long playerId, Long linkingId);
}
