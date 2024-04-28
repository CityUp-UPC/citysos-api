package com.citysos.api.citizen.domain.services;

import com.citysos.api.citizen.domain.models.entities.Citizen;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;

import java.util.List;
import java.util.Optional;

public interface CitizenService {
    Long createCitizen(CitizenRequest citizenRequest);
    Optional<Citizen> getCitizenById(Long id);
    List<Citizen> getAllCitizens();
    Citizen updateCitizen(Long id, CitizenRequest citizenRequest);
    void deleteCitizenById(Long id);
    Citizen logInCitizen(String email, String password);
    void updatePassword(Long id, String password);
}
