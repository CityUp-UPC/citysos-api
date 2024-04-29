package com.citysos.api;

import com.citysos.api.auth.domain.models.entities.RoleEntity;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import com.citysos.api.auth.infrastructure.repositories.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "CitySOS API", version = "1.0", description = "CitySOS API Information"))
public class ApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);

        String url = "http://localhost:8080/swagger-ui/index.html";
        System.out.println("\n• Swagger UI is available at » " + url);
    }

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Bean
    CommandLineRunner init() {
        return args -> {
            UserEntity userEntity1 = UserEntity.builder()
                    .email("wilver.ar.dev@gmail.com")
                    .username("wilver-ar")
                    .password(passwordEncoder.encode("1234"))
                    .phone("123456789")
                    .dni("72432916")
                    .name("Wilver Arana R.")
                    .roles(Set.of(RoleEntity.builder()
                            .role(ERole.valueOf(ERole.POLICE.name()))
                            .build()))
                    .build();

            UserEntity userEntity2 = UserEntity.builder()
                    .email("jack.ar.dev@gmail.com")
                    .username("jack-ar")
                    .password(passwordEncoder.encode("1234"))
                    .phone("123456789")
                    .dni("72432913")
                    .name("Jack Arana R.")
                    .roles(Set.of(RoleEntity.builder()
                            .role(ERole.valueOf(ERole.CITIZEN.name()))
                            .build()))
                    .build();

            userRepository.save(userEntity1);
            userRepository.save(userEntity2);
        };
    }
}
