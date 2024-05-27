package com.citysos.api.incident.domain.service;

import com.citysos.api.incident.domain.entity.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentService {
    List<Incident> fetchAll();
    List<Incident> fetchAllPendient();
    List<Incident> findIncidentHelp();

    List<Incident> fetchByDistrict(String district);
    List<Optional<?>> fetchByCitizenId(Integer citizenId);
    Optional<Incident> fetchById(Integer id);
    Incident save(Incident incident, Integer citizenId);


}
