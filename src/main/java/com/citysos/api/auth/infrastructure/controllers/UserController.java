package com.citysos.api.auth.infrastructure.controllers;

import com.citysos.api.auth.domain.models.entities.RoleEntity;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import com.citysos.api.auth.infrastructure.repositories.UserRepository;
import com.citysos.api.auth.infrastructure.resources.request.UserRequest;
import com.citysos.api.auth.infrastructure.resources.response.UserResponse;
import com.citysos.api.shared.domain.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "User", description = "The Users API")
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping("/sign-up/citizen")
    public ResponseEntity<UserResponse> signUpCitizen(@Valid @RequestBody UserRequest userRequest) {
        Set<RoleEntity> roles = Collections.singleton(RoleEntity.builder().role(ERole.valueOf(ERole.CITIZEN.name())).build());
        Long id = createUserEntity(userRequest, roles);
        UserEntity userCitizenCreated = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + id));
        UserResponse userCitizenResponse = modelMapper.map(userCitizenCreated, UserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCitizenResponse);
    }
    private Long createUserEntity(UserRequest userRequest, Set<RoleEntity> roles) {
        UserEntity userEntity = new UserEntity();
        modelMapper.map(userRequest, userEntity);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setRoles(roles);

        return userRepository.save(userEntity).getId();
    }
    @Transactional
    @PostMapping("/sign-up/police")
    public ResponseEntity<UserResponse> signUpPolice(@Valid @RequestBody UserRequest userRequest) {
        Set<RoleEntity> roles = Collections.singleton(RoleEntity.builder().role(ERole.valueOf(ERole.POLICE.name())).build());
        Long id = createUserEntity(userRequest, roles);
        UserEntity userPoliceCreated = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Police not found with id: " + id));
        UserResponse userPoliceResponse = modelMapper.map(userPoliceCreated, UserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userPoliceResponse);
    }
}