package com.upc.viksadventuresapi.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StudentName(String firstName, String lastName) {
    public StudentName() { this(null, null); }

    public StudentName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
    }
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}