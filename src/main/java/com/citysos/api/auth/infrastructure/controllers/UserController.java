package com.citysos.api.auth.infrastructure.controllers;

import com.citysos.api.auth.domain.models.entities.RoleEntity;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import com.citysos.api.auth.infrastructure.repositories.UserRepository;
import com.citysos.api.auth.infrastructure.resources.request.LoginRequest;
import com.citysos.api.auth.infrastructure.resources.request.UserRequest;
import com.citysos.api.shared.infrastructure.configuration.security.jwt.utils.JwtUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "User", description = "The Users API")
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/citizen/sign-up")
    public ResponseEntity<UserEntity> citizenSignUp(@Valid @RequestBody UserRequest userRequest) {

        Set<RoleEntity> roles = Collections.singleton(RoleEntity.builder().role(ERole.valueOf(ERole.CITIZEN.name())).build());

        UserEntity userEntity = UserEntity.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }
    @PostMapping("/police/sign-up")
    public ResponseEntity<UserEntity> policeSignUp(@Valid @RequestBody UserRequest userRequest) {

        Set<RoleEntity> roles = Collections.singleton(RoleEntity.builder().role(ERole.valueOf(ERole.POLICE.name())).build());

        UserEntity userEntity = UserEntity.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }
    @DeleteMapping("/citizen/{id}")
    public ResponseEntity<?> citizenDelete(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("User not found with id: " + id);
        }
        userRepository.deleteById(id);

        return ResponseEntity.ok("User deleted successfully with id: " + id);
    }
}
