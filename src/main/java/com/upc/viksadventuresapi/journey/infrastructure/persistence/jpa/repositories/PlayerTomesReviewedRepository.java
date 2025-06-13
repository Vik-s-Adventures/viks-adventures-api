package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerTomesReviewedRepository extends JpaRepository<PlayerTomesReviewed, Long> {
    Optional<PlayerTomesReviewed> findByPlayerAndConcept_Tome(Player player, Tome tome);
    boolean existsByPlayerAndConcept(Player player, Concept concept);
}
