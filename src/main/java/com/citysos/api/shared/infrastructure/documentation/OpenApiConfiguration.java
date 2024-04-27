package com.citysos.api.shared.infrastructure.documentation;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI cityUpOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("CityUp API")
                        .description("CitySOS | CityUp - application REST API documentation.")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("CityUp Wiki Documentation")
                        .url("https://github.com/WilverAR/crud.csv-backend/wiki"));
    }
}
