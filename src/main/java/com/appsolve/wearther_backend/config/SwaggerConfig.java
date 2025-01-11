package com.appsolve.wearther_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String API_NAME = "Appsolve";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "<h3>Appsolve API TEST</h3>";
    private static final String SECURITY_SCHEME_NAME = "BearerAuth";

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP) // HTTP 타입
                .scheme("bearer") // Bearer 인증 방식
                .bearerFormat("JWT"); // JWT 토큰 포맷 명시

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(SECURITY_SCHEME_NAME);

        Info info = new Info()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION);

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme))
                .info(info);    }
}
