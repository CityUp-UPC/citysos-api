package com.citysos.api.citizen.domain.services;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;

import java.util.List;
import java.util.Optional;

public interface CitizenService {
    Optional<UserEntity> getUserCitizenById(Long id);
    List<UserEntity> getAllUsersCitizens();
    UserEntity updateUserCitizen(Long id, CitizenRequest citizenRequest);
    void deleteUserCitizenById(Long id);
    void updatePasswordUserCitizen(Long id, String password);
}
