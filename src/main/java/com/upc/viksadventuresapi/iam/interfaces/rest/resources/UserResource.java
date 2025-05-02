package com.upc.viksadventuresapi.iam.interfaces.rest.resources;

import com.upc.viksadventuresapi.iam.domain.model.enums.AuthProvider;

public record UserResource(Long id, String name, String email, AuthProvider authProvider) {
}