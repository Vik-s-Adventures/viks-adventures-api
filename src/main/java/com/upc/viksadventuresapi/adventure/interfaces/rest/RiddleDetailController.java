package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteRiddleDetailCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleDetailByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleDetailsByRiddleIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.RiddleDetailCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.RiddleDetailQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateRiddleDetailResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.RiddleDetailResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateRiddleDetailCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.RiddleDetailResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/riddle-details", produces = "application/json")
@Tag(name = "Riddle Details", description = "Riddle Detail Management Endpoints")
public class RiddleDetailController {
    private final RiddleDetailCommandService riddleDetailCommandService;
    private final RiddleDetailQueryService riddleDetailQueryService;

    @PostMapping
    public ResponseEntity<RiddleDetailResource> createRiddleDetail(@RequestBody CreateRiddleDetailResource resource) {
        var command = CreateRiddleDetailCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = riddleDetailCommandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = RiddleDetailResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{riddleDetailId}")
    public ResponseEntity<RiddleDetailResource> getRiddleDetailById(@PathVariable Long riddleDetailId) {
        var query = new GetRiddleDetailByIdQuery(riddleDetailId);
        var result = riddleDetailQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resource = RiddleDetailResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/riddle/{riddleId}")
    public ResponseEntity<List<RiddleDetailResource>> getRiddleDetailsByRiddleId(@PathVariable Long riddleId) {
        var query = new GetRiddleDetailsByRiddleIdQuery(riddleId);
        var result = riddleDetailQueryService.handle(query);
        var resources = result.stream()
                .map(RiddleDetailResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{riddleDetailId}")
    public ResponseEntity<Void> deleteRiddleDetail(@PathVariable Long riddleDetailId) {
        var command = new DeleteRiddleDetailCommand(riddleDetailId);
        riddleDetailCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}