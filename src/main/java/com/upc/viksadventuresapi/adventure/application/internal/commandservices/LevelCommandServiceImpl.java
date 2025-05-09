package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLevelCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLevelCommand;
import com.upc.viksadventuresapi.adventure.domain.services.LevelCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LevelRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.WorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelCommandServiceImpl implements LevelCommandService {
    private final LevelRepository levelRepository;
    private final WorldRepository worldRepository;

    @Override
    public Optional<Level> handle(CreateLevelCommand command) {
        Optional<World> optionalWorld = worldRepository.findById(command.worldId());

        if (optionalWorld.isEmpty()) {
            throw new IllegalArgumentException("World with ID " + command.worldId() + " does not exist.");
        }

        World world = optionalWorld.get();
        var level = new Level(world, command);

        try {
            levelRepository.save(level);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving level: " + e.getMessage());
        }

        return Optional.of(level);
    }

    @Override
    public void handle(DeleteLevelCommand command) {
        if (!levelRepository.existsById(command.levelId())) {
            throw new IllegalArgumentException("Level with ID " + command.levelId() + " does not exist.");
        }
        try {
            levelRepository.deleteById(command.levelId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting level with ID " + command.levelId());
        }
    }
}
