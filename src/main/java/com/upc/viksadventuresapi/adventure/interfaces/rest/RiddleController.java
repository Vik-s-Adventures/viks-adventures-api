package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteRiddleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllRiddlesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddlesByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.RiddleCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.RiddleQueryService;
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
    private final RiddleCommandService riddleCommandService;
    private final RiddleQueryService riddleQueryService;

    @PostMapping
    public ResponseEntity<RiddleResource> createRiddle(@RequestBody CreateRiddleResource resource) {
        var command = CreateRiddleCommandFromResourceAssembler.toCommandFromResource(resource);
        var riddle = riddleCommandService.handle(command);
        if (riddle.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = RiddleResourceFromEntityAssembler.toResourceFromEntity(riddle.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{riddleId}")
    public ResponseEntity<RiddleResource> getRiddleById(@PathVariable Long riddleId) {
        var query = new GetRiddleByIdQuery(riddleId);
        var riddle = riddleQueryService.handle(query);
        if (riddle.isEmpty()) return ResponseEntity.notFound().build();
        var resource = RiddleResourceFromEntityAssembler.toResourceFromEntity(riddle.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/trial/{trialId}")
    public ResponseEntity<List<RiddleResource>> getRiddlesByTrialId(@PathVariable Long trialId) {
        var query = new GetRiddlesByTrialIdQuery(trialId);
        var riddles = riddleQueryService.handle(query);
        var resources = riddles.stream()
                .map(RiddleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<RiddleResource>> getAllRiddles() {
        var query = new GetAllRiddlesQuery();
        var riddles = riddleQueryService.handle(query);
        var resources = riddles.stream()
                .map(RiddleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{riddleId}")
    public ResponseEntity<Void> deleteRiddle(@PathVariable Long riddleId) {
        var deleteRiddleCommand = new DeleteRiddleCommand(riddleId);
        riddleCommandService.handle(deleteRiddleCommand);
        return ResponseEntity.noContent().build();
    }
}
