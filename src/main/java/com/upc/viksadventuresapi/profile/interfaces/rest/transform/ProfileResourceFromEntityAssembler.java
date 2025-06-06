package com.upc.viksadventuresapi.profile.interfaces.rest.transform;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getFullName(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getSex(),
                entity.getGradeLevel(),
                entity.getSchool()
        );
    }
}
