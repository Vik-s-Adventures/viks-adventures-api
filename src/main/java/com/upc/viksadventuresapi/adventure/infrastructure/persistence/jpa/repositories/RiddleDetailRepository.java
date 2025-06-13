package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiddleDetailRepository extends JpaRepository<RiddleDetail, Long> {
    List<RiddleDetail> findByRiddleId(Long id);
    List<RiddleDetail> findByRiddle(Riddle riddle);
}
