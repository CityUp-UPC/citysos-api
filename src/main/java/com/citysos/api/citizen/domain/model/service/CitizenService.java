package com.citysos.api.citizen.domain.model.service;

import com.citysos.api.citizen.domain.model.entity.Citizen;

import java.util.List;
import java.util.Optional;

public interface CitizenService {
    List<Citizen> fetchAll();
    Optional<Citizen> fetchById(Integer id);
    Citizen save(Citizen citizen);
}
