package com.citysos.api.auth.infrastructure.resources.response;

import com.citysos.api.auth.domain.models.entities.RoleEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String dni;
    private String name;
    private Set<RoleEntity> roles;
}