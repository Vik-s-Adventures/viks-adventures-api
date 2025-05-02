package com.upc.viksadventuresapi.profile.domain.services;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.domain.model.commands.CreateProfileCommand;
import com.upc.viksadventuresapi.profile.domain.model.commands.DeleteProfileByIdCommand;
import com.upc.viksadventuresapi.profile.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command, Long userId);
    void handle(DeleteProfileByIdCommand command);
}
