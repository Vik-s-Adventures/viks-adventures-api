package com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
