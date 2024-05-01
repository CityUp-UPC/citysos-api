package com.citysos.api.citizen.infrastructure.resources.response;


import com.citysos.api.auth.domain.models.entities.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class CitizenResponse {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String dni;
    private String name;
    private Set<RoleEntity> roles;
}
