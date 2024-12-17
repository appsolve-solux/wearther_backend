package com.appsolve.wearther_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String API_NAME = "Appsolve";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "<h3>Appsolve API TEST</h3>";

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION);

        return new OpenAPI().info(info);
    }
}
