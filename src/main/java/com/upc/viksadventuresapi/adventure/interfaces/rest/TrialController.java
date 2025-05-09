package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTrialCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTrialsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialsByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.TrialCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.TrialQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateTrialResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.TrialResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateTrialCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.TrialResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/trials", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Trials", description = "Trials Management Endpoints")
public class TrialController {

    private final TrialCommandService trialCommandService;
    private final TrialQueryService trialQueryService;

    @PostMapping
    public ResponseEntity<TrialResource> createTrial(@RequestBody CreateTrialResource resource) {
        var command = CreateTrialCommandFromResourceAssembler.toCommandFromResource(resource);
        var trial = trialCommandService.handle(command);
        if (trial.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = TrialResourceFromEntityAssembler.toResourceFromEntity(trial.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{trialId}")
    public ResponseEntity<TrialResource> getTrialById(@PathVariable Long trialId) {
        var query = new GetTrialByIdQuery(trialId);
        var trial = trialQueryService.handle(query);
        if (trial.isEmpty()) return ResponseEntity.notFound().build();
        var resource = TrialResourceFromEntityAssembler.toResourceFromEntity(trial.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<TrialResource>> getTrialsByLevelId(@PathVariable Long levelId) {
        var query = new GetTrialsByLevelIdQuery(levelId);
        var trials = trialQueryService.handle(query);
        var resources = trials.stream()
                .map(TrialResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<TrialResource>> getAllTrials() {
        var query = new GetAllTrialsQuery();
        var trials = trialQueryService.handle(query);
        var resources = trials.stream()
                .map(TrialResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{trialId}")
    public ResponseEntity<Void> deleteTrial(@PathVariable Long trialId) {
        var command = new DeleteTrialCommand(trialId);
        trialCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}