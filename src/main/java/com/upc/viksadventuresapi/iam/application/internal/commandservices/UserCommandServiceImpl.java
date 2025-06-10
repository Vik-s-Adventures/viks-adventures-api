package com.upc.viksadventuresapi.iam.application.internal.commandservices;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.iam.domain.model.commands.DeleteUserByIdCommand;
import com.upc.viksadventuresapi.iam.domain.model.commands.LoginUserGoogleCommand;
import com.upc.viksadventuresapi.iam.domain.model.commands.LoginUserLocalCommand;
import com.upc.viksadventuresapi.iam.domain.model.commands.RegisterUserLocalCommand;
import com.upc.viksadventuresapi.iam.domain.model.enums.AuthProvider;
import com.upc.viksadventuresapi.iam.domain.services.UserCommandService;
import com.upc.viksadventuresapi.iam.infrastructure.authorization.configuration.JwtService;
import com.upc.viksadventuresapi.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerCommandService;
import com.upc.viksadventuresapi.profile.domain.model.commands.CreateProfileCommand;
import com.upc.viksadventuresapi.profile.domain.services.ProfileCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileCommandService profileCommandService;
    private final PlayerCommandService playerCommandService;
    private final JwtService jwtService;

    @Override
    public Optional<User> handle(RegisterUserLocalCommand command) {
        // Verificar si ya existe el email
        if (userRepository.findByEmail(command.email()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        // Crear el usuario
        User user = new User();
        user.setName(command.name());
        user.setEmail(command.email());
        user.setPassword(passwordEncoder.encode(command.password()));
        user.setAuthProvider(AuthProvider.LOCAL);

        // Guardar el usuario
        User savedUser = userRepository.save(user);

        // Crear el perfil
        CreateProfileCommand createProfileCommand = new CreateProfileCommand(savedUser.getId());
        profileCommandService.handle(createProfileCommand);

        CreatePlayerCommand createPlayerCommand = new CreatePlayerCommand(savedUser.getId(), 0);
        playerCommandService.handle(createPlayerCommand);

        return Optional.of(savedUser);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(LoginUserLocalCommand command) {
        User existingUser;

        if (command.identifier().contains("@")) {
            existingUser = userRepository.findByEmail(command.identifier()).orElse(null);
        } else {
            existingUser = userRepository.findByName(command.identifier()).orElse(null);
        }

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(command.password(), existingUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(existingUser);
        return Optional.of(ImmutablePair.of(existingUser, token));
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(LoginUserGoogleCommand command) {
        OAuth2AuthenticationToken oauthToken = command.token();
        String email = oauthToken.getPrincipal().getAttribute("email");
        String name = oauthToken.getPrincipal().getAttribute("name");

        // Buscar si ya existe un usuario con ese email
        Optional<User> existingUserOpt = userRepository.findByEmail(email);

        User user;
        if (existingUserOpt.isPresent()) {
            user = existingUserOpt.get();
        } else {
            // Si no existe, registrar un nuevo usuario
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(null);
            user.setAuthProvider(AuthProvider.GOOGLE);
            user = userRepository.save(user);

            CreateProfileCommand profileCommand = new CreateProfileCommand(user.getId());
            profileCommandService.handle(profileCommand);
        }

        String jwtToken = jwtService.generateToken(user);
        return Optional.of(ImmutablePair.of(user, jwtToken));
    }

    @Override
    public void handle(DeleteUserByIdCommand command) {
        // Verificar si el usuario existe
        User user = userRepository.findById(command.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        // Eliminar el usuario
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting user: " + e.getMessage());
        }
    }
}
