package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrialRepository extends JpaRepository<Trial, Long> {
    List<Trial> findByLevelId(Long levelId);
}
