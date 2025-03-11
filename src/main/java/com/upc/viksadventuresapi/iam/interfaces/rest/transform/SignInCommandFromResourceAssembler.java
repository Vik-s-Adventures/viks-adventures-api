package com.upc.viksadventuresapi.iam.interfaces.rest.transform;

import com.upc.viksadventuresapi.iam.domain.model.commands.SignInCommand;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
