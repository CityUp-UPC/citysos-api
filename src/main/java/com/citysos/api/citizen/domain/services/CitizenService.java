package com.citysos.api.citizen.domain.services;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;

import java.util.List;
import java.util.Optional;

public interface CitizenService {
    Long getCitizenId();
    Optional<UserEntity> getUserCitizen();
    List<UserEntity> getAllUsersCitizens();
    UserEntity updateUserCitizen(CitizenRequest citizenRequest);
    void deleteUserCitizen();
    void updatePasswordUserCitizen(String newPassword, String confirmPassword);
}
