package com.upc.viksadventuresapi.profile.interfaces.rest;

import com.upc.viksadventuresapi.profile.domain.model.queries.GetAllLearningPathData;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByIdQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByProfileIdQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByQuizIdQuery;
import com.upc.viksadventuresapi.profile.domain.services.LearningPathDataQueryService;
import com.upc.viksadventuresapi.profile.interfaces.rest.resources.LearningPathDataResource;
import com.upc.viksadventuresapi.profile.interfaces.rest.transform.LearningResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/learning-paths", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Learning Paths", description = "Learning Path Management Endpoints")
public class LearningPathDataController {
    private final LearningPathDataQueryService learningPathDataQueryService;

    public LearningPathDataController(LearningPathDataQueryService learningPathDataQueryService) {
        this.learningPathDataQueryService = learningPathDataQueryService;
    }

    /**
     * Gets a LearningPathData by its id
     * @param learningPathDataId the id of the LearningPathData to get
     * @return the LearningPathData resource associated with the given id
     */
    @GetMapping("/{learningPathDataId}")
    public ResponseEntity<LearningPathDataResource> getLearningPathDataById(@PathVariable Long learningPathDataId) {
        var getLearningPathDataByIdQuery = new GetLearningPathDataByIdQuery(learningPathDataId);
        var learningPathData = learningPathDataQueryService.handle(getLearningPathDataByIdQuery);
        if (learningPathData.isEmpty()) return ResponseEntity.badRequest().build();
        
        // Convertir la entidad a recurso
        var learningPathDataResource = LearningResourceFromEntityAssembler.toResourceFromEntity(learningPathData.get());
        return ResponseEntity.ok(learningPathDataResource);
    }

    /**
     * Gets all the LearningPaths
     * @return a list of all the LearningPathData resources currently stored
     */
    @GetMapping
    public ResponseEntity<List<LearningPathDataResource>> getAllLearningPaths() {
        var getAllLearningPathDataQuery = new GetAllLearningPathData();
        var learningPaths = learningPathDataQueryService.handle(getAllLearningPathDataQuery);
        var learningPathResources = learningPaths.stream()
                .map(LearningResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(learningPathResources);
    }

    /**
     * Gets LearningPaths by ProfileId
     * @param profileId the id of the profile to get LearningPaths for
     * @return the list of LearningPathData resources for the given profile
     */
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<LearningPathDataResource>> getLearningPathsByProfileId(@PathVariable Long profileId) {
        var getLearningPathDataByProfileIdQuery = new GetLearningPathDataByProfileIdQuery(profileId);
        var learningPaths = learningPathDataQueryService.handle(getLearningPathDataByProfileIdQuery);
        var learningPathResources = learningPaths.stream()
                .map(LearningResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(learningPathResources);
    }

    /**
     * Gets LearningPaths by QuizId
     * @param quizId the id of the quiz to get LearningPaths for
     * @return the list of LearningPathData resources for the given quiz
     */
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<LearningPathDataResource>> getLearningPathsByQuizId(@PathVariable Long quizId) {
        var getLearningPathDataByQuizIdQuery = new GetLearningPathDataByQuizIdQuery(quizId);
        var learningPaths = learningPathDataQueryService.handle(getLearningPathDataByQuizIdQuery);
        var learningPathResources = learningPaths.stream()
                .map(LearningResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(learningPathResources);
    }
}
