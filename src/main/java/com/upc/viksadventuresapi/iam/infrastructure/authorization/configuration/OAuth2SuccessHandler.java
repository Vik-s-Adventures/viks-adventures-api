package com.upc.viksadventuresapi.iam.infrastructure.authorization.configuration;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.iam.domain.model.commands.LoginUserGoogleCommand;
import com.upc.viksadventuresapi.iam.domain.services.UserCommandService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${frontend.redirect.url}")
    private String frontendRedirectUrl;

    private final UserCommandService userCommandService;
    private final JwtService jwtService;

    public OAuth2SuccessHandler(JwtService jwtService,
                                @Lazy UserCommandService userCommandService)
    {
        this.userCommandService = userCommandService;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        // Usa tu CQRS para manejar autenticación
        var command = new LoginUserGoogleCommand(token);
        var authenticatedUser = userCommandService.handle(command);

        if (authenticatedUser.isEmpty()) {
            response.sendRedirect(frontendRedirectUrl + "?error=unauthorized");
            return;
        }

        User user = authenticatedUser.get().getLeft(); // ✅ obtenemos el User real
        String jwt = jwtService.generateToken(user);   // ✅ generamos el JWT correctamente


        // Redirigir al frontend con el token en la URL
        response.sendRedirect(frontendRedirectUrl + "?token=" + jwt);
    }
}
