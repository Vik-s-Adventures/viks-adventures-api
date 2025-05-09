package com.upc.viksadventuresapi.profile.interfaces.rest;

import com.upc.viksadventuresapi.iam.infrastructure.authorization.configuration.JwtService;
import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.domain.model.commands.DeleteProfileByIdCommand;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetAllProfilesQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetProfileByIdQuery;
import com.upc.viksadventuresapi.profile.domain.services.ProfileCommandService;
import com.upc.viksadventuresapi.profile.domain.services.ProfileQueryService;
import com.upc.viksadventuresapi.profile.interfaces.rest.resources.UpdateProfileResource;
import com.upc.viksadventuresapi.profile.interfaces.rest.resources.ProfileResource;
import com.upc.viksadventuresapi.profile.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import com.upc.viksadventuresapi.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ProfilesController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the Profile entity.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;
    private final JwtService jwtService;

    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService, JwtService jwtService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
        this.jwtService = jwtService;
    }

    /**
     * Updates a Profile
     * @param resource the Profile resource to update
     * @param authorizationHeader the authorization header containing the JWT token
     * @return the updated Profile resource
     */

    @PutMapping
    public ResponseEntity<ProfileResource> updateProfile(
            @RequestBody UpdateProfileResource resource,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extraer el token
        String token = authorizationHeader.replace("Bearer ", "");

        // Obtener userId desde el token
        Long userId = jwtService.extractUserId(token);

        // Convertir el recurso a comando
        var command = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(resource);

        // Ejecutar la lógica de negocio
        Optional<Profile> optionalProfile = profileCommandService.handle(command, userId);
        if (optionalProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Convertir entidad a recurso para retornar
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Gets a Profile by its id
     * @param profileId the id of the Profile to get
     * @return the Profile resource associated to given Profile id
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Gets all the Profiles
     * @return a list of all the Profile resources currently stored
     */
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }

    /**
     * Gets a Profile by its userId
     * @param userId the id of the user to get the Profile for
     * @return the Profile resource associated with given user id
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId) {
        var getProfileByUserIdQuery = new GetProfileByIdQuery(userId);
        var profile = profileQueryService.handle(getProfileByUserIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Deletes a Profile by its id
     * @param profileId the id of the Profile to delete
     * @return a response entity with no content
     */
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfileById(@PathVariable Long profileId) {
        var deleteProfileByIdCommand = new DeleteProfileByIdCommand(profileId);
        profileCommandService.handle(deleteProfileByIdCommand);
        return ResponseEntity.noContent().build();
    }
}
