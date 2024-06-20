package com.citysos.api.incident.service;

import com.citysos.api.citizen.domain.model.persistence.CitizenRepository;
import com.citysos.api.incident.domain.entity.Incident;
import com.citysos.api.incident.domain.persistence.IncidentRepository;
import com.citysos.api.incident.domain.service.IncidentService;
import com.citysos.api.notification.domain.entity.NotificationMessage;
import com.citysos.api.notification.service.FirebaseMessagingService;
import com.citysos.api.police.domain.model.entity.Police;
import com.citysos.api.police.domain.service.PoliceService;
import com.citysos.api.shared.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IncidentServiceImpl implements IncidentService {
    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    PoliceService policeService;

    @Autowired
    FirebaseMessagingService  firebaseMessagingService;

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
        Incident savedIncident = incidentRepository.save(incident);
        notifyNearByPolices(savedIncident, 1);
        return savedIncident;
    }

    @Override
    public void notifyNearByPolices(Incident incident, Integer radius){
        double incidentLat;
        double incidentLon;

        try {
            incidentLat = Double.parseDouble(incident.getLatitude());
            incidentLon = Double.parseDouble(incident.getLongitude());
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid latitude or longitude format", HttpStatus.BAD_REQUEST);
        }

        List<Police> nearbyPolices = policeService.findNearbyPolices(incidentLat, incidentLon, radius);

        for (Police police : nearbyPolices) {
            String recipientToken = police.getUser().getDeviceToken();
            if (recipientToken == null || recipientToken.isEmpty()) {
                System.out.println("Police " + police.getId() + " does not have a valid device token.");
                continue; // Skip this police if token is not present
            }

            System.out.println("Notification sent to police " + police.getId() + ": " + police.getUser().getUsername()); //QUITAR ES PRUEBA
            NotificationMessage notificationMessage = new NotificationMessage();
            notificationMessage.setRecipientToken(recipientToken);
            notificationMessage.setTitle("New Incident Alert");
            notificationMessage.setBody("A new incident has been reported at " + incident.getAddress());
            notificationMessage.setImage(null); // Set if you have an image URL
            Map<String, String> data = new HashMap<>();
            data.put("incidentId", incident.getId().toString());
            notificationMessage.setData(data);

            String response = firebaseMessagingService.sendNotificationByToken(notificationMessage);
        }
    }

    @Override
    public List<Incident> getPendingIncidentsByCitizenId(Integer citizenId) {
        return incidentRepository.findByStatusPendingByCitizenId(citizenId);
    }
}
