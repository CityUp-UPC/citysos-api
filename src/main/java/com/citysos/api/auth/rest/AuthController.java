package com.citysos.api.auth.rest;

import com.citysos.api.auth.resource.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.citysos.api.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth", description = "the auth API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<AuthResponse> login(@RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(authService.changePassword(request));
    }

    @PostMapping("/register-police")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestPolice request) {
        return ResponseEntity.ok(authService.registerPolice(request));
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestUser request) {
        return ResponseEntity.ok(authService.registerCitizen(request));
    }

    @GetMapping("/exist-user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(authService.findUserById(id));
    }

}
