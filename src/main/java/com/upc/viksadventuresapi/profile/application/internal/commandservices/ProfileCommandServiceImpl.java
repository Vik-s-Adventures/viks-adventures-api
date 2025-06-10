package com.upc.viksadventuresapi.profile.application.internal.commandservices;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.domain.model.commands.CreateProfileCommand;
import com.upc.viksadventuresapi.profile.domain.model.commands.DeleteProfileByIdCommand;
import com.upc.viksadventuresapi.profile.domain.model.commands.UpdateProfileCommand;
import com.upc.viksadventuresapi.profile.domain.model.valueobjects.*;
import com.upc.viksadventuresapi.profile.domain.services.ProfileCommandService;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class  ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        // Verify user exists
        Optional<User> optionalUser = userRepository.findById(command.userId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + command.userId() + " does not exist.");
        }
        User user = optionalUser.get();

        var profile = new Profile(user);
        profileRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command, Long userId) {
        System.out.println("Recibiendo solicitud para actualizar el perfil del usuario con ID: " + userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.println("No se encontró un usuario con ID: " + userId);
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
        User user = optionalUser.get();
        System.out.println("Usuario encontrado: " + user.getName() + " con ID: " + user.getId());

        // Obtener el perfil del usuario
        Optional<Profile> optionalProfile = profileRepository.findByUserId(userId);
        if (optionalProfile.isEmpty()) {
            System.out.println("No se encontró un perfil asociado al usuario con ID: " + userId);
            throw new IllegalArgumentException("Profile for user ID " + userId + " does not exist.");
        }
        Profile profile = optionalProfile.get();
        System.out.println("Perfil encontrado para el usuario con ID: " + userId + ", Perfil ID: " + profile.getId());

        // Actualizar los campos
        profile.setFullName(new FullName(command.firstName(), command.lastName()));
        profile.setBirthDate(BirthDate.fromString(command.birthDate()));
        profile.setSex(new Sex(command.sex()));
        profile.setGradeLevel(new GradeLevel(command.gradeLevel()));
        profile.setSchool(new School(command.school()));

        // Guardar cambios
        profileRepository.save(profile);
        System.out.println("Perfil actualizado con éxito para el usuario con ID: " + userId);
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
