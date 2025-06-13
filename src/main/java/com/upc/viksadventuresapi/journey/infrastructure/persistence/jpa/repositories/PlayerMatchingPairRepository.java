package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMatchingPairRepository extends JpaRepository<PlayerMatchingPair, Long> {
    List<PlayerMatchingPair> findAllByPlayerIdAndMatchingItemA_MatchingTrialLevelId(Long playerId, Long levelId);

    List<PlayerMatchingPair> findAllByPlayerIdAndMatchingItemA_MatchingId(Long id, Long id1);
}
