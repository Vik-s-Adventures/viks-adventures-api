package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkingRepository extends JpaRepository<Linking, Long> {
    List<Linking> findByTrialId(Long trialId);
}
