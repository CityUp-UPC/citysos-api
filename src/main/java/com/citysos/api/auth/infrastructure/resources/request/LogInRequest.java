package com.citysos.api.auth.infrastructure.resources.request;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class LogInRequest {
    private String username;
    private String password;
}
