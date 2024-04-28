package com.citysos.api.auth.infrastructure.controllers;

import com.citysos.api.auth.domain.models.entities.RoleEntity;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.infrastructure.repositories.UserRepository;
import com.citysos.api.auth.infrastructure.resources.request.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/api/test/user")
    public String userAccess() {
        return ">>> User Contents!";
    }
    @GetMapping("/api/test/pm")
    public String projectManagementAccess() {
        return ">>> Project Management Board";
    }
    @PostMapping("/api/test/admin")
    public ResponseEntity<?> adminAccess(@Valid @RequestBody UserRequest userRequest) {
        //VERIFY THE FUNCTIONALITY OF THE CODE BELOW...
        Set<RoleEntity> roles = userRequest.getRoles().stream().map(x -> modelMapper.map(x, RoleEntity.class)).collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }
    @DeleteMapping("/api/test/admin/{id}")
    public ResponseEntity<?> adminDelete(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("User not found with id: " + id);
        }
        userRepository.deleteById(id);

        return ResponseEntity.ok("User deleted successfully with id: " + id);
    }
}
