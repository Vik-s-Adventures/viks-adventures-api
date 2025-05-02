package com.upc.viksadventuresapi.iam.domain.model.commands;

public record LoginUserLocalCommand(
        String identifier,
        String password
) {
}
