package com.citysos.api.police.service;

import com.citysos.api.incident.domain.entity.Incident;
import com.citysos.api.incident.domain.persistence.IncidentRepository;
import com.citysos.api.police.domain.model.entity.Police;
import com.citysos.api.police.domain.persistence.PoliceRepository;
import com.citysos.api.police.domain.service.PoliceService;
import com.citysos.api.shared.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    private PoliceRepository policeRepository;

    @Autowired
    private IncidentRepository incidentRepository;

    public PoliceServiceImpl(PoliceRepository policeRepository, IncidentRepository incidentRepository){
        this.policeRepository=policeRepository;
        this.incidentRepository = incidentRepository;
    }

    @Override
    public List<Police> fetchAll() {
        return policeRepository.findAll();
    }

    @Override
    public Optional<Police> fetchById(Integer id) {
        return policeRepository.findById(id);
    }

    @Override
    public Police save(Police police) {
        return policeRepository.save(police);
    }

    @Override
    public void joinIncident(Integer id, Integer incidentId) {
        String aux = incidentRepository.findById(incidentId).get().getStatus().toString();
        if (aux.equals("COMPLETED") || aux.equals("IN_PROGRESS")) { //if (aux.equals(Status.COMPLETED.toString()) || aux.equals(Status.IN_PROGRESS.toString())) {
            throw new CustomException("Invalid status: The incident cannot be in COMPLETED or IN_PROGRESS status", HttpStatus.ACCEPTED);
        } else {
            boolean exists = incidentRepository.existsPoliceInIncident(id, incidentId) > 0;
            if(exists){
                throw new CustomException("Police is already assigned to this incident", HttpStatus.CONFLICT);
            }
            else {
                incidentRepository.insertIncidentPolice(id, incidentId);
                incidentRepository.updateIncidentStatus(incidentId);
                throw new CustomException("Police successfully joined the incident", HttpStatus.ACCEPTED);
            }
        }
    }


    @Override
    public void completedIncident(Integer incidentId) {
        String aux = incidentRepository.findById(incidentId).get().getStatus().toString();
        if (!aux.equals("COMPLETED") && !aux.equals("PENDIENT")) { //if (!aux.equals(Status.COMPLETED.toString()) && !aux.equals(Status.PENDIENT.toString())) {
            incidentRepository.completedIncident(incidentId);
            throw new CustomException("Incident successfully completed", HttpStatus.ACCEPTED);
        } else {
            throw new CustomException("Invalid status: The incident cannot be in COMPLETED or PENDIENT status", HttpStatus.CONFLICT);
        }
    }

    @Override
    public void requestReinforcements(Integer incidentId) {
        String aux = incidentRepository.findById(incidentId).get().getStatus().toString();
        if (!aux.equals("COMPLETED") && !aux.equals("PENDIENT") && !aux.equals("HELP_REQUIRED")) { //if (!aux.equals(Status.COMPLETED.toString()) && !aux.equals(Status.PENDIENT.toString()) && !aux.equals(Status.HELP_REQUIRED.toString())) {
            incidentRepository.requestReinforcements(incidentId);
            throw new CustomException("Reinforcements successfully requested for the incident", HttpStatus.ACCEPTED);
        } else {
            throw new CustomException("Invalid status: The incident cannot be in COMPLETED or PENDIENT status", HttpStatus.ACCEPTED);
        }
    }


}
