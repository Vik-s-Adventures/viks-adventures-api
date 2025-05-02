package com.upc.viksadventuresapi.iam.interfaces.rest.transform;

import com.upc.viksadventuresapi.iam.domain.model.commands.RegisterUserLocalCommand;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.RegisterUserLocalResource;

public class RegisterUserLocalCommandFromResourceAssembler {
    public static RegisterUserLocalCommand toCommandFromResource(RegisterUserLocalResource resource) {
        return new RegisterUserLocalCommand(
                resource.name(),
                resource.email(),
                resource.password()
        );
    }
}
