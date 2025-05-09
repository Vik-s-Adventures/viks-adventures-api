package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateFinalBattleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteFinalBattleCommand;
import com.upc.viksadventuresapi.adventure.domain.services.FinalBattleCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.FinalBattleRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinalBattleCommandServiceImpl implements FinalBattleCommandService {
    private final FinalBattleRepository finalBattleRepository;
    private final LevelRepository levelRepository;

    @Override
    public Optional<FinalBattle> handle(CreateFinalBattleCommand command) {
        Optional<Level> optionalLevel = levelRepository.findById(command.levelId());

        if (optionalLevel.isEmpty()){
            throw new IllegalArgumentException("Quiz with ID " + command.levelId() + " does not exist.");
        }

        Level level = optionalLevel.get();
        var finalBattle = new FinalBattle(level, command);

        try {
            finalBattleRepository.save(finalBattle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving final battle: " + e.getMessage());
        }

        return Optional.of(finalBattle);
    }

    @Override
    public void handle(DeleteFinalBattleCommand command) {
        if (!finalBattleRepository.existsById(command.finalBattleId())) {
            throw new IllegalArgumentException("Final battle with ID " + command.finalBattleId() + " does not exist.");
        }
        try {
            finalBattleRepository.deleteById(command.finalBattleId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting final battle with ID " + command.finalBattleId());
        }
    }
}
