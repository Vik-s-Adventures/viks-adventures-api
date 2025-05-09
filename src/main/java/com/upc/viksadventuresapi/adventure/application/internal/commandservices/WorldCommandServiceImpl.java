package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateWorldCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteWorldCommand;
import com.upc.viksadventuresapi.adventure.domain.services.WorldCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.WorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorldCommandServiceImpl implements WorldCommandService {
    private final WorldRepository worldRepository;

    @Override
    public Optional<World> handle(CreateWorldCommand command) {
        Optional<World> optionalWorld = worldRepository.findByName_Name(command.name());

        if (optionalWorld.isPresent()) {
            throw new IllegalArgumentException("World with name " + command.name() + " already exists.");
        }

        var world = new World(command);

        try {
            worldRepository.save(world);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving world: " + e.getMessage());
        }

        return Optional.of(world);
    }

    @Override
    public void handle(DeleteWorldCommand command) {
        if (!worldRepository.existsById(command.worldId())) {
            throw new IllegalArgumentException("World with ID " + command.worldId() + " does not exist.");
        }
        try {
            worldRepository.deleteById(command.worldId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting world with ID " + command.worldId());
        }
    }
}
