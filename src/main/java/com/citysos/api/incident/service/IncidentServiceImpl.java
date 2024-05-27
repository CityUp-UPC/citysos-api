package com.citysos.api.incident.service;

import com.citysos.api.citizen.domain.model.persistence.CitizenRepository;
import com.citysos.api.incident.domain.entity.Incident;
import com.citysos.api.incident.domain.persistence.IncidentRepository;
import com.citysos.api.incident.domain.service.IncidentService;
import com.citysos.api.shared.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {
    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    CitizenRepository citizenRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository, CitizenRepository citizenRepository){
        this.incidentRepository=incidentRepository;
        this.citizenRepository=citizenRepository;
    }
    @Override
    public List<Incident> fetchAll() {
        return incidentRepository.findAll();
    }

    @Override
    public List<Incident> fetchAllPendient() {
        return incidentRepository.findIncidentPendient();
    }

    @Override
    public List<Incident> findIncidentHelp() {
        return incidentRepository.findIncidentHelp();
    }

    @Override
    public List<Incident> fetchByDistrict(String district) {
        return incidentRepository.findByDistrict(district);
    }

    @Override
    public List<Optional<?>> fetchByCitizenId(Integer citizenId) {
        return Collections.singletonList(Optional.of(incidentRepository.findByCitizenId(citizenId)));
    }

    @Override
    public Optional<Incident> fetchById(Integer id) {
        return incidentRepository.findById(id);
    }

    @Override
    public Incident save(Incident incident, Integer citizenId) {
        incident.setCitizen(citizenRepository.findById(citizenId).orElseThrow(() -> new CustomException("Citizen not found", HttpStatus.NOT_FOUND)));
        incident.setId(null);
        return incidentRepository.save(incident);
    }

}
