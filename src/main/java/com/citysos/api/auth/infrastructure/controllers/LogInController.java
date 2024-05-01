package com.citysos.api.auth.infrastructure.controllers;

import com.citysos.api.auth.infrastructure.resources.request.LogInRequest;
import com.citysos.api.shared.infrastructure.configuration.security.jwt.utils.JwtUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "LogIn", description = "The LogIn API")
public class LogInController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/log-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LogInRequest logInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInRequest.getUsername(),
                            logInRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String JsonWebToken = jwtUtils.generateAccessToken(authentication.getName());

            Map<String, Object> response = new HashMap<>();
            response.put("Message", "Authentication successful");
            response.put("Username", authentication.getName());
            response.put("Password", passwordEncoder.encode(logInRequest.getPassword()));
            response.put("Role", authentication.getAuthorities());
            response.put("Token", JsonWebToken);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
