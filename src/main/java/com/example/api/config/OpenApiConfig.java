package com.example.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Customers Controller - Seguros Unimed",
        version = "${projectVersion:0.0.1-SNAPSHOT}",
        description = "Teste para Java Backend da Seguros Unimed."
))
public class OpenApiConfig {

}