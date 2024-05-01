package com.citysos.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "CitySOS API", version = "1.0", description = "CitySOS API Information"))
public class ApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);

        String url = "http://localhost:8080/swagger-ui/index.html";
        System.out.println("\n• Swagger UI is available at » " + url);
    }
}
