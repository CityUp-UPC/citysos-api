package com.citysos.api.auth.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestPolice {
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;
    String dni;
    String deviceToken;

    String assignedDistrict;
    //Boolean inService;
    String policeRank;
    String policeIdentifier;
    String entityPolice;
}
