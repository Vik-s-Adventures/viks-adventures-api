package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLinkingCommand;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TrialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkingCommandServiceImpl implements LinkingCommandService
{
    private final TrialRepository trialRepository;
    private final LinkingRepository linkingRepository;

    @Override
    public Optional<Linking> handle(CreateLinkingCommand command) {
        Optional<Trial> optionalTrial = trialRepository.findById(command.trialId());

        if (optionalTrial.isEmpty()) {
            throw new IllegalArgumentException("Trial with ID " + command.trialId() + " does not exist.");
        }

        Trial trial = optionalTrial.get();
        var linking = new Linking(trial, command);

        try {
            linkingRepository.save(linking);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving linking: " + e.getMessage());
        }

        return Optional.of(linking);
    }

    @Override
    public void handle(DeleteLinkingCommand command) {
        if (!linkingRepository.existsById(command.linkingId())) {
            throw new IllegalArgumentException("Linking with ID " + command.linkingId() + " does not exist.");
        }
        try {
            linkingRepository.deleteById(command.linkingId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting linking with ID " + command.linkingId());
        }
    }
}
