package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteConceptCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllConceptsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetConceptByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetConceptsByTomeIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.ConceptCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.ConceptQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.ConceptResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateConceptResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.ConceptResourceFromEntityAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateConceptCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/concepts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Concepts", description = "Concepts Management Endpoints")
public class ConceptController {

    private final ConceptCommandService conceptCommandService;
    private final ConceptQueryService conceptQueryService;

    @PostMapping
    public ResponseEntity<ConceptResource> createConcept(@RequestBody CreateConceptResource resource) {
        var command = CreateConceptCommandFromResourceAssembler.toCommandFromResource(resource);
        var concept = conceptCommandService.handle(command);
        if (concept.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = ConceptResourceFromEntityAssembler.toResourceFromEntity(concept.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{conceptId}")
    public ResponseEntity<ConceptResource> getConceptById(@PathVariable Long conceptId) {
        var query = new GetConceptByIdQuery(conceptId);
        var concept = conceptQueryService.handle(query);
        if (concept.isEmpty()) return ResponseEntity.notFound().build();
        var resource = ConceptResourceFromEntityAssembler.toResourceFromEntity(concept.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/tome/{tomeId}")
    public ResponseEntity<List<ConceptResource>> getConceptsByTomeId(@PathVariable Long tomeId) {
        var query = new GetConceptsByTomeIdQuery(tomeId);
        var concepts = conceptQueryService.handle(query);
        var resources = concepts.stream()
                .map(ConceptResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<ConceptResource>> getAllConcepts() {
        var query = new GetAllConceptsQuery();
        var concepts = conceptQueryService.handle(query);
        var resources = concepts.stream()
                .map(ConceptResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{conceptId}")
    public ResponseEntity<Void> deleteConcept(@PathVariable Long conceptId) {
        var command = new DeleteConceptCommand(conceptId);
        conceptCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
