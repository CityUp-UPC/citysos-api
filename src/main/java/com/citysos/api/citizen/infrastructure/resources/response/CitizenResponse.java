package com.citysos.api.citizen.infrastructure.resources.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CitizenResponse {

    private Long id;

    private String name;

    private String email;

    private String password;
}
