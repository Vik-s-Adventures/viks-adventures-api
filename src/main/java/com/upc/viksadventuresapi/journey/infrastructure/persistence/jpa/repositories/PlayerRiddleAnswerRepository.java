package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRiddleAnswerRepository extends JpaRepository<PlayerRiddleAnswer, Long> {
    List<PlayerRiddleAnswer> findAllByPlayerIdAndRiddleDetailRiddleId(Long playerId, Long riddleDetailId);
    Optional<PlayerRiddleAnswer> findByPlayerIdAndRiddleDetailId(Long playerId, Long riddleDetailId);
    boolean existsByPlayerIdAndRiddleDetailId(Long playerId, Long riddleDetailId);
}
