package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllMatchesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchesByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateMatchingResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.MatchingResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateMatchingCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.MatchingResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/matches", produces = "application/json")
@Tag(name = "Matches", description = "Matches Management Endpoints")
public class MatchingController {
    private final MatchingCommandService matchingCommandService;
    private final MatchingQueryService matchingQueryService;

    @PostMapping
    public ResponseEntity<MatchingResource> createMatch(@RequestBody CreateMatchingResource resource) {
        var command = CreateMatchingCommandFromResourceAssembler.toCommandFromResource(resource);
        var matching = matchingCommandService.handle(command);
        if (matching.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = MatchingResourceFromEntityAssembler.toResourceFromEntity(matching.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{matchingId}")
    public ResponseEntity<MatchingResource> getMatchById(@PathVariable Long matchingId) {
        var query = new GetMatchingByIdQuery(matchingId);
        var matching = matchingQueryService.handle(query);
        if (matching.isEmpty()) return ResponseEntity.notFound().build();
        var resource = MatchingResourceFromEntityAssembler.toResourceFromEntity(matching.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/trial/{trialId}")
    public ResponseEntity<List<MatchingResource>> getMatchesByTrialId(@PathVariable Long trialId) {
        var query = new GetMatchesByTrialIdQuery(trialId);
        var matches = matchingQueryService.handle(query);
        var resources = matches.stream()
                .map(MatchingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<MatchingResource>> getAllMatches() {
        var query = new GetAllMatchesQuery();
        var matches = matchingQueryService.handle(query);
        var resources = matches.stream()
                .map(MatchingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{matchingId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchingId) {
        var deleteMatchingCommand = new DeleteMatchingCommand(matchingId);
        matchingCommandService.handle(deleteMatchingCommand);
        return ResponseEntity.noContent().build();
    }
}
