package com.upc.viksadventuresapi.profile.application.internal.commandservices;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.domain.model.commands.CreateProfileCommand;
import com.upc.viksadventuresapi.profile.domain.model.commands.DeleteProfileByIdCommand;
import com.upc.viksadventuresapi.profile.domain.services.ProfileCommandService;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {

        var profile = new Profile(
                command.firstName(),
                command.lastName(),
                command.birthDate(),
                command.sex(),
                command.gradeLevel(),
                command.school()
        );
        profileRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    public void handle(DeleteProfileByIdCommand command) {
        if (!profileRepository.existsById(command.profileId())) {
            throw new IllegalArgumentException("Profile with ID " + command.profileId() + " does not exist.");
        }
        try {
            profileRepository.deleteById(command.profileId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting profile with ID " + command.profileId());
        }
    }
}
