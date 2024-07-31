package com.dgarcia.booktsore.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI catalogServiceAPI(){
        return new OpenAPI()
        .info(new Info().title("Catalog Service API")
        .description("This is the REST Api for Catalog Service")
        .version("v0.0.1")
        .license(new License().name("Apache 2.0")))
        .externalDocs(new ExternalDocumentation()
        .description("You can refer to the Catalog Service Documentation ")
        .url("https://catalog-service-dummy-url.com/docs"));
        
    }
}
