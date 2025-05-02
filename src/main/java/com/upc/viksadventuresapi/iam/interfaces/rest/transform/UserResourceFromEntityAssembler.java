package com.upc.viksadventuresapi.iam.interfaces.rest.transform;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user){
        return new UserResource(user.getId(), user.getName(), user.getEmail(), user.getAuthProvider());
    }
}
