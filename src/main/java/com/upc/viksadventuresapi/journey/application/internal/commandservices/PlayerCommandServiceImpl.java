package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerCommandServiceImpl implements PlayerCommandService {
    private final ProfileRepository profileRepository;
    private final PlayerRepository playerRepository;

    @Override
    public Optional<Player> handle(CreatePlayerCommand command) {
        Optional<Profile> optionalProfile = profileRepository.findById(command.profileId());

        if(optionalProfile.isEmpty()){
            throw new IllegalArgumentException("Profile with ID " + command.profileId() + " does not exist.");
        }

        Profile profile = optionalProfile.get();
        var player = new Player(profile, command);

        try {
            playerRepository.save(player);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving player: " + e.getMessage());
        }

        return Optional.of(player);
    }

    @Override
    public void handle(DeletePlayerCommand command) {
        if (!playerRepository.existsById(command.playerId())) {
            throw new IllegalArgumentException("Player with ID " + command.playerId() + " does not exist.");
        }
        try {
            playerRepository.deleteById(command.playerId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting player with ID " + command.playerId() + ": " + e.getMessage());
        }
    }
}
