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
import java.util.stream.Collectors;

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
        if (!aux.equals("COMPLETED")) { //if (!aux.equals(Status.COMPLETED.toString()) && !aux.equals(Status.PENDIENT.toString())) {
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

    @Override
    public void updateLocation(Integer id, String latitude, String longitude) {
        Police police = policeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Police not found", HttpStatus.NOT_FOUND));
        police.setLatitude(latitude);
        police.setLongitude(longitude);
        policeRepository.save(police);
    }

    @Override
    public List<Police> findNearbyPolices(double incidentLat, double incidentLon, Integer radius) {
        return policeRepository.findPoliceInService().stream()
                .filter(police -> {
                    double policeLat = Double.parseDouble(police.getLatitude());
                    double policeLon = Double.parseDouble(police.getLongitude());
                    double distance = calculateDistance(incidentLat, incidentLon, policeLat, policeLon);
                    return distance <= radius; // Define the range in km
                })
                .collect(Collectors.toList());
    }
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }
    @Override
    public void updateInService(Integer id) {
        if(policeRepository.findById(id).get().getInService()){
            policeRepository.OffInService(id);
        }
        else {
            policeRepository.OnInService(id);
        }

    }

    @Override
    public Police fetchByUserId(Integer userId) {
        return policeRepository.findByUserId(userId);
    }
}
