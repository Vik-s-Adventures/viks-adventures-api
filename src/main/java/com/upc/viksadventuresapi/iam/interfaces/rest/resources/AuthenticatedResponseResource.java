package com.upc.viksadventuresapi.iam.interfaces.rest.resources;

public record AuthenticatedResponseResource(UserResource user, String token) { }
