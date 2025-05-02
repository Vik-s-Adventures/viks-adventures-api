package com.upc.viksadventuresapi.profile.domain.model.commands;

public record UpdateProfileCommand(
        String firstName,
        String lastName,
        String birthDate,
        String sex,
        String gradeLevel,
        String school
) {
}