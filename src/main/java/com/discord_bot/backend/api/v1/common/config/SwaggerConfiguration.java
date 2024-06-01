package com.discord_bot.backend.api.v1.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "LostArk DiscordBot API Document",
                description = "LostArk DiscordBot Api 문서",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        Components components = new Components();

        return new OpenAPI()
                .components(components);
    }
}
