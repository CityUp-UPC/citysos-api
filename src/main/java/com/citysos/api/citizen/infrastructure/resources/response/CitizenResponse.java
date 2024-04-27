package com.citysos.api.citizen.infrastructure.resources.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenResponse {

    private Long id;

    private String name;

    private String email;

    private String password;
}
