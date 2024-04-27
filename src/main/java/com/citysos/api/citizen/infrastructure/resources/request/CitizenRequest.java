package com.citysos.api.citizen.infrastructure.resources.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CitizenRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
