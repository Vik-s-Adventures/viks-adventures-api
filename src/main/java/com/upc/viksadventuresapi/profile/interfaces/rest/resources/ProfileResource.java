package com.upc.viksadventuresapi.profile.interfaces.rest.resources;

public record ProfileResource(Long id,
                              String fullName,
                              String birthDate,
                              String sex,
                              String gradeLevel,
                              String school
                              ) {
}
