package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingPairByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingPairsByMatchingIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingPairCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingPairQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.*;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/matching-pairs", produces = "application/json")
@Tag(name = "Matching Pairs", description = "Matching Pair Management Endpoints")
public class MatchingPairController {

    private final MatchingPairCommandService matchingPairCommandService;
    private final MatchingPairQueryService matchingPairQueryService;

    @PostMapping
    public ResponseEntity<MatchingPairResource> createMatchingPair(@RequestBody CreateMatchingPairResource resource) {
        var command = CreateMatchingPairCommandFromResourceAssembler.toCommandFromResource(resource);
        var matchingPair = matchingPairCommandService.handle(command);
        if (matchingPair.isEmpty()) return ResponseEntity.badRequest().build();
        var response = MatchingPairResourceFromEntityAssembler.toResourceFromEntity(matchingPair.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{matchingPairId}")
    public ResponseEntity<MatchingPairResource> getMatchingPairById(@PathVariable Long matchingPairId) {
        var query = new GetMatchingPairByIdQuery(matchingPairId);
        var pair = matchingPairQueryService.handle(query);
        if (pair.isEmpty()) return ResponseEntity.notFound().build();
        var resource = MatchingPairResourceFromEntityAssembler.toResourceFromEntity(pair.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/matching/{matchingId}")
    public ResponseEntity<List<MatchingPairResource>> getMatchingPairsByMatchingId(@PathVariable Long matchingId) {
        var query = new GetMatchingPairsByMatchingIdQuery(matchingId);
        var pairs = matchingPairQueryService.handle(query);
        var resources = pairs.stream()
                .map(MatchingPairResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{matchingPairId}")
    public ResponseEntity<Void> deleteMatchingPair(@PathVariable Long matchingPairId) {
        var command = new DeleteMatchingPairCommand(matchingPairId);
        matchingPairCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
