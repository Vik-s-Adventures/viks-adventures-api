package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateConceptCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteConceptCommand;
import com.upc.viksadventuresapi.adventure.domain.services.ConceptCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ConceptRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConceptCommandServiceImpl implements ConceptCommandService {
    private final ConceptRepository conceptRepository;
    private final TomeRepository tomeRepository;

    @Override
    public Optional<Concept> handle(CreateConceptCommand command) {
        Optional<Tome> optionalTome = tomeRepository.findById(command.tomeId());

        if (optionalTome.isEmpty()){
            throw new IllegalArgumentException("Tome with ID " + command.tomeId() + " does not exist.");
        }

        Tome tome = optionalTome.get();
        var concept = new Concept(tome, command);

        try {
            conceptRepository.save(concept);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving concept: " + e.getMessage());
        }

        return Optional.of(concept);
    }

    @Override
    public void handle(DeleteConceptCommand command) {
        if (!conceptRepository.existsById(command.conceptId())) {
            throw new IllegalArgumentException("Concept with ID " + command.conceptId() + " does not exist.");
        }
        try {
            conceptRepository.deleteById(command.conceptId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting concept with ID " + command.conceptId());
        }
    }
}
