package com.upc.viksadventuresapi.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Embeddable
public record BirthDate(LocalDate birthDate) {
    public BirthDate {
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be null or in the future.");
        }
    }

    public static BirthDate fromString(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            return new BirthDate(parsedDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid birth date format. Expected dd/MM/yyyy.");
        }
    }

    public String formatted() {
        return birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
