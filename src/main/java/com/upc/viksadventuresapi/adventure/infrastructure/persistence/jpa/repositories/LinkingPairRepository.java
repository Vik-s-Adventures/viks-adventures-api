package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkingPairRepository extends JpaRepository<LinkingPair, Long> {
    List<LinkingPair> findByLinkingId(Long linkingId);
}
