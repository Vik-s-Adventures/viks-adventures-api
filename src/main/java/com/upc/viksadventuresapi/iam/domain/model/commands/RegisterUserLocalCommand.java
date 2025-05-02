package com.upc.viksadventuresapi.iam.domain.model.commands;

public record RegisterUserLocalCommand(
        String name,
        String email,
        String password
) {
}
