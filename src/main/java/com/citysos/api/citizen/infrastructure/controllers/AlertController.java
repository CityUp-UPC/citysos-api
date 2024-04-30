package com.citysos.api.citizen.infrastructure.controllers;

import com.citysos.api.citizen.domain.models.aggregates.Alert;
import com.citysos.api.citizen.domain.services.AlertService;
import com.citysos.api.citizen.infrastructure.resources.request.AlertRequest;
import com.citysos.api.citizen.infrastructure.resources.response.AlertResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/alert", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Alerts", description = "The Alerts API")
public class AlertController {
    private final AlertService alertService;
    private final ModelMapper modelMapper;
    public AlertController(AlertService alertService, ModelMapper modelMapper) {
        this.alertService = alertService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @PostMapping("/emit")
    public ResponseEntity<AlertResponse> emit(@Valid @RequestBody AlertRequest alertRequest) {
        Long id = alertService.createAlert(alertRequest);
        Alert alertCreated = alertService.getAlertById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));
        AlertResponse alertResponse = modelMapper.map(alertCreated, AlertResponse.class);
        alertResponse.setUserId(alertCreated.getUser().getId()); // THIS LINE IS FOR GETTING THE USER ID...!!!;,V

        return ResponseEntity.status(HttpStatus.CREATED).body(alertResponse);
    }


    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<AlertResponse> getById(@PathVariable Long id) {
        Alert alert = alertService.getAlertById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));
        AlertResponse alertResponse = modelMapper.map(alert, AlertResponse.class);
        alertResponse.setUserId(alert.getUser().getId()); // THIS LINE IS FOR GETTING THE USER ID...!!!;,V

        return ResponseEntity.status(HttpStatus.OK).body(alertResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping("/record")
    public ResponseEntity<List<AlertResponse>> getAll() {
        List<Alert> alerts = alertService.getAllAlerts();
        List<AlertResponse> alertResponse = new ArrayList<>();
        alerts.forEach(alert -> {
            AlertResponse response = modelMapper.map(alert, AlertResponse.class);
            response.setUserId(alert.getUser().getId());
            alertResponse.add(response);
        });
        return ResponseEntity.status(HttpStatus.OK).body(alertResponse);
    }

    @Transactional
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        alertService.deleteAlert(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
