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

import java.util.List;

@RestController
@RequestMapping(value = "/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
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

        return ResponseEntity.status(HttpStatus.CREATED).body(alertResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<AlertResponse> getById(@PathVariable Long id) {
        Alert alert = alertService.getAlertById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));
        AlertResponse alertResponse = modelMapper.map(alert, AlertResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(alertResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping("/record")
    public ResponseEntity<List<AlertResponse>> getAll() {
        List<Alert> alerts = alertService.getAllAlerts();
        List<AlertResponse> alertResponse = alerts.stream().map(x -> modelMapper.map(x, AlertResponse.class)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(alertResponse);
    }

    @Transactional
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        alertService.deleteAlert(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
