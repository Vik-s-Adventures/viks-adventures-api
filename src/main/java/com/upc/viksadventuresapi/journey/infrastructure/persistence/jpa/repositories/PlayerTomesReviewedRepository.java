package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerTomesReviewedRepository extends JpaRepository<PlayerTomesReviewed, Long> {
    Optional<PlayerTomesReviewed> findByPlayerProgressAndConcept_Tome(PlayerProgress progress, Tome tome);
}
