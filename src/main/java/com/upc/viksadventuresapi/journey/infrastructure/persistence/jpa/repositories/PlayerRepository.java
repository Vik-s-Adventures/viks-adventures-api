package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
