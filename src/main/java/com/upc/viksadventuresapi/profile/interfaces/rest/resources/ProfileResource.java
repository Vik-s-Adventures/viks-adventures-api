package com.upc.viksadventuresapi.profile.interfaces.rest.resources;

public record ProfileResource(Long id,
                              Long userId,
                              String fullName,
                              String firstName,
                              String lastName,
                              String birthDate,
                              String sex,
                              String gradeLevel,
                              String school
                              ) {
}
