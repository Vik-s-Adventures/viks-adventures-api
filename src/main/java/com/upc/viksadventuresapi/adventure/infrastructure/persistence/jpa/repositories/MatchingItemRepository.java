package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingItemRepository extends JpaRepository<MatchingItem, Long> {
    List<MatchingItem> findByMatchingPairId(Long matchingPairId);
}
