package com.upc.viksadventuresapi.iam.interfaces.rest;

import com.upc.viksadventuresapi.iam.domain.model.commands.LoginUserGoogleCommand;
import com.upc.viksadventuresapi.iam.domain.services.UserCommandService;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.AuthenticatedResponseResource;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.LoginUserLocalResource;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.RegisterUserLocalResource;
import com.upc.viksadventuresapi.iam.interfaces.rest.resources.UserResource;
import com.upc.viksadventuresapi.iam.interfaces.rest.transform.AuthenticatedResponseResourceFromEntityAssembler;
import com.upc.viksadventuresapi.iam.interfaces.rest.transform.LoginUserLocalCommandFromResourceAssembler;
import com.upc.viksadventuresapi.iam.interfaces.rest.transform.RegisterUserLocalCommandFromResourceAssembler;
import com.upc.viksadventuresapi.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserCommandService userCommandService;

    @PostMapping("/register")
    public ResponseEntity<UserResource> registerLocal(@RequestBody RegisterUserLocalResource resource) {
        var command = RegisterUserLocalCommandFromResourceAssembler.toCommandFromResource(resource);
        var createdUser = userCommandService.handle(command);

        if (createdUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(createdUser.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @PostMapping("/login/local")
    public ResponseEntity<AuthenticatedResponseResource> loginLocal(@RequestBody LoginUserLocalResource resource) {
        var command = LoginUserLocalCommandFromResourceAssembler.toCommandFromResource(resource);
        var authenticatedUser = userCommandService.handle(command);

        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var resourceResponse = AuthenticatedResponseResourceFromEntityAssembler
                .toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());

        return ResponseEntity.ok(resourceResponse);
    }

    @GetMapping("/login/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    @GetMapping("/google/success")
    public ResponseEntity<AuthenticatedResponseResource> handleGoogleSuccess(OAuth2AuthenticationToken token) {
        var command = new LoginUserGoogleCommand(token);
        var authenticatedUser = userCommandService.handle(command);

        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var resource = AuthenticatedResponseResourceFromEntityAssembler
                .toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/loginFailure")
    public ResponseEntity<String> loginFailure() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error: Falló la autenticación con Google.");
    }
}