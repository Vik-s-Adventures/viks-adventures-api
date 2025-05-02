package com.upc.viksadventuresapi.iam.interfaces.rest.transform;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.AuthenticatedResponseResource;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.UserResource;

public class AuthenticatedResponseResourceFromEntityAssembler {
    public static AuthenticatedResponseResource toResourceFromEntity(User user, String token) {
        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return new AuthenticatedResponseResource(userResource, token);
    }
}