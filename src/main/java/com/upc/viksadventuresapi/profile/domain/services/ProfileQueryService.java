package com.upc.viksadventuresapi.profile.domain.services;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetAllProfilesQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetProfileByIdQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetProfileByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByUserIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> findByUserId(Long userId);
}
