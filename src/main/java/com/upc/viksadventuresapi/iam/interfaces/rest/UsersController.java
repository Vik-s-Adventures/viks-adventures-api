package com.upc.viksadventuresapi.iam.interfaces.rest;

import com.upc.viksadventuresapi.iam.domain.model.commands.DeleteUserByIdCommand;
import com.upc.viksadventuresapi.iam.domain.model.queries.GetAllUsersQuery;
import com.upc.viksadventuresapi.iam.domain.model.queries.GetUserByIdQuery;
import com.upc.viksadventuresapi.iam.domain.services.UserCommandService;
import com.upc.viksadventuresapi.iam.domain.services.UserQueryService;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.UserResource;
import com.upc.viksadventuresapi.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
@RequiredArgsConstructor
public class UsersController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var query = new GetAllUsersQuery();
        var users = userQueryService.handle(query);
        var resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var query = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(query);

        return user.map(value ->
                        ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        var command = new DeleteUserByIdCommand(userId);
        userCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}