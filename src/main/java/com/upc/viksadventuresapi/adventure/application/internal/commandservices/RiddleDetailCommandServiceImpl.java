package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleDetailCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteRiddleDetailCommand;
import com.upc.viksadventuresapi.adventure.domain.services.RiddleDetailCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.RiddleDetailRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.RiddleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiddleDetailCommandServiceImpl implements RiddleDetailCommandService {
    private final RiddleRepository riddleRepository;
    private final RiddleDetailRepository riddleDetailRepository;

    @Override
    public Optional<RiddleDetail> handle(CreateRiddleDetailCommand command) {
        Optional<Riddle> optionalRiddle = riddleRepository.findById(command.riddleId());

        if (optionalRiddle.isEmpty()) {
            throw new IllegalArgumentException("Riddle with ID " + command.riddleId() + " does not exist.");
        }

        Riddle riddle = optionalRiddle.get();
        var riddleDetail = new RiddleDetail(riddle, command);

        try {
            riddleDetailRepository.save(riddleDetail);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving riddle detail: " + e.getMessage());
        }

        return Optional.of(riddleDetail);
    }

    @Override
    public void handle(DeleteRiddleDetailCommand command) {
        if (!riddleDetailRepository.existsById(command.riddleDetailId())) {
            throw new IllegalArgumentException("Riddle detail with ID " + command.riddleDetailId() + " does not exist.");
        }
        try {
            riddleDetailRepository.deleteById(command.riddleDetailId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting riddle detail with ID " + command.riddleDetailId());
        }
    }
}
