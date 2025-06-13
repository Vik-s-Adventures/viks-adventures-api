package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerLinkingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerLinkingPairByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerLinkingPairCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerLinkingPairQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerLinkingPairResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.SavePlayerLinkingResponseResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/player-linking-pairs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Player Linking Pairs", description = "Linking Pair Tracking Endpoints")
public class PlayerLinkingPairController {

    private final PlayerLinkingPairCommandService commandService;
    private final PlayerLinkingPairQueryService queryService;

    @PostMapping("/save")
    public ResponseEntity<Void> savePlayerLinkingPair(@RequestBody SavePlayerLinkingResponseResource resource) {
        var command = SavePlayerLinkingPairCommandFromResourceAssembler.toCommandFromResource(resource);
        commandService.handle(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerLinkingPairResource> getByPlayerLinkingPairId(@PathVariable Long id) {
        var query = new GetPlayerLinkingPairByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var response = PlayerLinkingPairResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerLinkingPair(@PathVariable Long id) {
        var command = new DeletePlayerLinkingPairCommand(id);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}