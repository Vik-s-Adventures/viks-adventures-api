package com.upc.viksadventuresapi.profile.domain.model.commands;

public record CreateProfileCommand(
        Long userId,
        String firstName,
        String lastName,
        String birthDate,
        String sex,
        String gradeLevel,
        String school)
{ }

