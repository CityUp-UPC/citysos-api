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
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "User", description = "The Users API")
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up/citizen")
    public ResponseEntity<UserEntity> signUpCitizen(@Valid @RequestBody UserRequest userRequest) {

        Set<RoleEntity> roles = Collections.singleton(RoleEntity.builder().role(ERole.valueOf(ERole.CITIZEN.name())).build());
        UserEntity userCitizenEntity = getUserEntityResponse(userRequest, roles);
        userRepository.save(userCitizenEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCitizenEntity);
    }

    
    private UserEntity getUserEntityResponse(UserRequest userRequest, Set<RoleEntity> roles) {

        return UserEntity.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .phone(userRequest.getPhone())
                .dni(userRequest.getDni())
                .name(userRequest.getName())
                .roles(roles)
                .build();
    }
    @PostMapping("/sign-up/police")
    public ResponseEntity<UserEntity> signUpPolice(@Valid @RequestBody UserRequest userRequest) {

        Set<RoleEntity> roles = Collections.singleton(RoleEntity.builder().role(ERole.valueOf(ERole.POLICE.name())).build());
        UserEntity userPoliceEntity = getUserEntityResponse(userRequest, roles);
        userRepository.save(userPoliceEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userPoliceEntity);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping("/all/citizen")
    public ResponseEntity<List<UserResponse>> getAllCitizens() {
        List<UserEntity> userCitizenEntities = userRepository.findUsersByRole(ERole.CITIZEN);
        List<UserResponse> userResponses = userCitizenEntities.stream()
                .map(x -> modelMapper.map(x, UserResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    @GetMapping("/all/police")
    public ResponseEntity<List<UserResponse>> getAllPolices() {
        ERole role = ERole.POLICE;
        List<UserEntity> userCitizenEntities = userRepository.findUsersByRole(ERole.POLICE);
        List<UserResponse> userResponses = userCitizenEntities.stream()
                .map(x -> modelMapper.map(x, UserResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }
}