package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingPairRepository extends JpaRepository<MatchingPair, Long> {
    List<MatchingPair> findByMatchingId(Long matchingId);
    List<MatchingPair> findByMatching(Matching matching);
}
