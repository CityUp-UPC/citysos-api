package com.citysos.api.auth.infrastructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Role", description = "The Roles API")
public class RoleController {

    @PreAuthorize("hasRole('CITIZEN')")
    @GetMapping("/accessCitizen")
    public String accessCitizen() {
        return ">>> Citizen Contents!";
    }

    @PreAuthorize("hasRole('POLICE')")
    @GetMapping("/accessPolice")
    public String accessPolice() {
        return ">>> Police Contents!";
    }
}
