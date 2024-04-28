package com.citysos.api;

import com.citysos.api.auth.domain.models.entities.RoleEntity;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import com.citysos.api.auth.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);

        String url = "http://localhost:8080/swagger-ui.html";
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
                    .roles(Set.of(RoleEntity.builder()
                            .role(ERole.valueOf(ERole.ADMIN.name()))
                            .build()))
                    .build();

            UserEntity userEntity2 = UserEntity.builder()
                    .email("jack.ar.dev@gmail.com")
                    .username("jack-ar")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(RoleEntity.builder()
                            .role(ERole.valueOf(ERole.USER.name()))
                            .build()))
                    .build();

            UserEntity userEntity3 = UserEntity.builder()
                    .email("brandon.ar.dev@gmail.com")
                    .username("brandon-ar")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(RoleEntity.builder()
                            .role(ERole.valueOf(ERole.INVITED.name()))
                            .build()))
                    .build();

            userRepository.save(userEntity1);
            userRepository.save(userEntity2);
            userRepository.save(userEntity3);
        };
    }
}
