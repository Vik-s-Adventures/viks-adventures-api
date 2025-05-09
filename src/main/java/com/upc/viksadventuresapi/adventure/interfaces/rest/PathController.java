package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeletePathCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllPathsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathsByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.PathCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.PathQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreatePathResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.PathResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreatePathCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.PathResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/paths", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Paths", description = "Paths Management Endpoints")
public class PathController {
    private final PathCommandService pathCommandService;
    private final PathQueryService pathQueryService;

    @PostMapping
    public ResponseEntity<PathResource> createPath(@RequestBody CreatePathResource resource) {
        var createPathCommand = CreatePathCommandFromResourceAssembler.toCommandFromResource(resource);
        var path = pathCommandService.handle(createPathCommand);
        if (path.isEmpty()) return ResponseEntity.badRequest().build();
        var pathResource = PathResourceFromEntityAssembler.toResourceFromEntity(path.get());
        return ResponseEntity.status(201).body(pathResource);
    }

    @GetMapping("/{pathId}")
    public ResponseEntity<PathResource> getPathById(@PathVariable Long pathId) {
        var getPathByIdQuery = new GetPathByIdQuery(pathId);
        var path = pathQueryService.handle(getPathByIdQuery);
        if (path.isEmpty()) return ResponseEntity.notFound().build();
        var pathResource = PathResourceFromEntityAssembler.toResourceFromEntity(path.get());
        return ResponseEntity.ok(pathResource);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<PathResource>> getPathsByLevelId(@PathVariable Long levelId) {
        var getPathsByLevelIdQuery = new GetPathsByLevelIdQuery(levelId);
        var paths = pathQueryService.handle(getPathsByLevelIdQuery);
        var pathResources = paths.stream()
                .map(PathResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pathResources);
    }

    @GetMapping
    public ResponseEntity<List<PathResource>> getAllPaths() {
        var getAllPathsQuery = new GetAllPathsQuery();
        var paths = pathQueryService.handle(getAllPathsQuery);
        var pathResources = paths.stream()
                .map(PathResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pathResources);
    }

    @DeleteMapping("/{pathId}")
    public ResponseEntity<Void> deletePath(@PathVariable Long pathId) {
        var deletePathCommand = new DeletePathCommand(pathId);
        pathCommandService.handle(deletePathCommand);
        return ResponseEntity.noContent().build();
    }
}
