package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerRiddleAnswerByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerRiddleAnswerCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerRiddleAnswerQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerRiddleAnswerResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerRiddleAnswerResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerRiddleAnswerResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.CreatePlayerRiddleAnswerCommandFromResourceAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerRiddleAnswerResourceFromEntityAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.UpdatePlayerRiddleAnswerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/player-riddle-answers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Player Riddle Answers", description = "Riddle Answer Tracking Endpoints")
public class PlayerRiddleAnswerController {
    private final PlayerRiddleAnswerCommandService commandService;
    private final PlayerRiddleAnswerQueryService queryService;

    @PostMapping
    public ResponseEntity<PlayerRiddleAnswerResource> createPlayerRiddleAnswer(@RequestBody CreatePlayerRiddleAnswerResource resource) {
        var command = CreatePlayerRiddleAnswerCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerRiddleAnswerResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping
    public ResponseEntity<PlayerRiddleAnswerResource> updatePlayerRiddleAnswer(@RequestBody UpdatePlayerRiddleAnswerResource resource) {
        var command = UpdatePlayerRiddleAnswerCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerRiddleAnswerResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerRiddleAnswerResource> getByPlayerRiddleAnswerId(@PathVariable Long id) {
        var query = new GetPlayerRiddleAnswerByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var response = PlayerRiddleAnswerResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerRiddleAnswer(@PathVariable Long id) {
        var command = new DeletePlayerRiddleAnswerCommand(id);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
