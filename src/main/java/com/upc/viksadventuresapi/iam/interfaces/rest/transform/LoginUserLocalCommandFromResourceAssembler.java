package com.upc.viksadventuresapi.iam.interfaces.rest.transform;

import com.upc.viksadventuresapi.iam.domain.model.commands.LoginUserLocalCommand;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.LoginUserLocalResource;

public class LoginUserLocalCommandFromResourceAssembler {
    public static LoginUserLocalCommand toCommandFromResource(LoginUserLocalResource resource) {
        return new LoginUserLocalCommand(
                resource.identifier(),
                resource.password()
        );
    }
}
