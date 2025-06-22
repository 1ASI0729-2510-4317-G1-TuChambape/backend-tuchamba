package com.acme.jobconnect.platform.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Le dice a Spring que esta clase contiene configuración
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth"; // Puede ser cualquier nombre

        return new OpenAPI()
                // Añade el candado de autorización a TODOS los endpoints de la API
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // Define el esquema de seguridad que se usará
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP) // Usamos autenticación de tipo HTTP
                                        .scheme("bearer")               // El esquema específico es "bearer"
                                        .bearerFormat("JWT")            // El formato del token es JWT
                        )
                )
                // Define la información general de la API
                .info(new Info()
                        .title("JobConnect Platform API")
                        .version("v1.0.0")
                        .description("API para la plataforma de conexión de trabajos JobConnect.")
                );
    }
}
