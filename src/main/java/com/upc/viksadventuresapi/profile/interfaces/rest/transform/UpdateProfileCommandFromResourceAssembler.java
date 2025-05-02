package com.upc.viksadventuresapi.profile.interfaces.rest.transform;

import com.upc.viksadventuresapi.profile.domain.model.commands.UpdateProfileCommand;
import com.upc.viksadventuresapi.profile.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.birthDate(),
                resource.sex(),
                resource.gradeLevel(),
                resource.school()
        );
    }
}
