package com.upc.viksadventuresapi.profile.domain.model.commands;

public record CreateProfileCommand(
        String firstName,
        String lastName,
        String sex,
        String birthDate,
        String gradeLevel,
        String school)
{ }

