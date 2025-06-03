package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllRiddlesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddlesByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.TrialRiddleCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.TrialRiddleQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateRiddleResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.RiddleResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateRiddleCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.RiddleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/riddles", produces = "application/json")
@Tag(name = "Riddles", description = "Riddles Management Endpoints")
public class RiddleController {
    private final TrialRiddleCommandService trialRiddleCommandService;
    private final TrialRiddleQueryService trialRiddleQueryService;

    @PostMapping
    public ResponseEntity<RiddleResource> createTrialRiddle(@RequestBody CreateRiddleResource resource) {
        var command = CreateRiddleCommandFromResourceAssembler.toCommandFromResource(resource);
        var trialRiddle = trialRiddleCommandService.handle(command);
        if (trialRiddle.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = RiddleResourceFromEntityAssembler.toResourceFromEntity(trialRiddle.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{trialRiddleId}")
    public ResponseEntity<RiddleResource> getTrialRiddleById(@PathVariable Long trialRiddleId) {
        var query = new GetRiddleByIdQuery(trialRiddleId);
        var trialRiddle = trialRiddleQueryService.handle(query);
        if (trialRiddle.isEmpty()) return ResponseEntity.notFound().build();
        var resource = RiddleResourceFromEntityAssembler.toResourceFromEntity(trialRiddle.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/trial/{trialId}")
    public ResponseEntity<List<RiddleResource>> getTrialRiddlesByTrialId(@PathVariable Long trialId) {
        var query = new GetRiddlesByTrialIdQuery(trialId);
        var trialRiddles = trialRiddleQueryService.handle(query);
        var resources = trialRiddles.stream()
                .map(RiddleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<RiddleResource>> getAllTrialRiddles() {
        var query = new GetAllRiddlesQuery();
        var trialRiddles = trialRiddleQueryService.handle(query);
        var resources = trialRiddles.stream()
                .map(RiddleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
