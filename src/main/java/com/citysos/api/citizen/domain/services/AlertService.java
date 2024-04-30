package com.citysos.api.citizen.domain.services;

import com.citysos.api.citizen.domain.models.aggregates.Alert;
import com.citysos.api.citizen.infrastructure.resources.request.AlertRequest;

import java.util.List;
import java.util.Optional;

public interface AlertService {
    Long createAlert(AlertRequest alertRequest);
    Optional<Alert> getAlertById(Long id);
    List<Alert> getAllAlerts();
    void deleteAlert(Long id);
}
