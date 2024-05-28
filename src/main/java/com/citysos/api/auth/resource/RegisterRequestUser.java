package com.citysos.api.auth.resource;

import com.citysos.api.auth.domain.model.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestUser {
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;
    String dni;
    String deviceToken;

    String district;
}