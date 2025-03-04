package com.upc.viksadventuresapi.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenapiConfiguration {
    @Bean

    public OpenAPI arquimentorPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Vik's Adventures Platform API")
                        .description("Vik's Adventures  Platform application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Vik's Adventures  Platform Wiki Documentation")
                        .url("https://viks-adventures-platform.wiki.github.io/docs"));
        return openApi;
    }

}
