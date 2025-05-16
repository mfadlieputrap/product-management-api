package com.example.product_managemen_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("D2C Store API")
                        .version("1.0.0")
                        .description("Dokumentasi API untuk D2C Fashion Brand Store"));
    }
}