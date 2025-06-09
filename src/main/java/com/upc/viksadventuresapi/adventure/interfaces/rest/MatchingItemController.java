package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingItemCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingItemByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingItemsByMatchingPairIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingItemCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingItemQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateMatchingItemResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.MatchingItemResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateMatchingItemCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.MatchingItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/matching-items", produces = "application/json")
@Tag(name = "Matching Items", description = "Matching Item Management Endpoints")
public class MatchingItemController {
    private final MatchingItemCommandService matchingItemCommandService;
    private final MatchingItemQueryService matchingItemQueryService;

    @PostMapping
    public ResponseEntity<MatchingItemResource> createMatchingItem(@RequestBody CreateMatchingItemResource resource) {
        var command = CreateMatchingItemCommandFromResourceAssembler.toCommandFromResource(resource);
        var matchingItem = matchingItemCommandService.handle(command);
        if (matchingItem.isEmpty()) return ResponseEntity.badRequest().build();
        var response = MatchingItemResourceFromEntityAssembler.toResourceFromEntity(matchingItem.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{matchingItemId}")
    public ResponseEntity<MatchingItemResource> getMatchingItemById(@PathVariable Long matchingItemId) {
        var query = new GetMatchingItemByIdQuery(matchingItemId);
        var item = matchingItemQueryService.handle(query);
        if (item.isEmpty()) return ResponseEntity.notFound().build();
        var resource = MatchingItemResourceFromEntityAssembler.toResourceFromEntity(item.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/pair/{matchingPairId}")
    public ResponseEntity<List<MatchingItemResource>> getMatchingItemsByPair(@PathVariable Long matchingPairId) {
        var query = new GetMatchingItemsByMatchingPairIdQuery(matchingPairId);
        var items = matchingItemQueryService.handle(query);
        var resources = items.stream()
                .map(MatchingItemResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/matching/{matchingId}")
    public ResponseEntity<List<MatchingItemResource>> getMatchingItemsByMatching(@PathVariable Long matchingId) {
        var query = new GetMatchingItemsByMatchingPairIdQuery(matchingId);
        var items = matchingItemQueryService.handle(query);
        var resources = items.stream()
                .map(MatchingItemResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{matchingItemId}")
    public ResponseEntity<Void> deleteMatchingItem(@PathVariable Long matchingItemId) {
        var command = new DeleteMatchingItemCommand(matchingItemId);
        matchingItemCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}