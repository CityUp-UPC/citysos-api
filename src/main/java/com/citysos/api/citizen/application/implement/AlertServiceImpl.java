package com.citysos.api.citizen.application.implement;

import com.citysos.api.citizen.domain.models.aggregates.Alert;
import com.citysos.api.citizen.domain.models.enums.EStatus;
import com.citysos.api.citizen.domain.services.AlertService;
import com.citysos.api.citizen.infrastructure.repositories.AlertRepository;
import com.citysos.api.citizen.infrastructure.resources.request.AlertRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final ModelMapper modelMapper;
    public AlertServiceImpl(AlertRepository alertRepository, ModelMapper modelMapper) {
        this.alertRepository = alertRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long createAlert(Alert alertRequest) {
        Alert alert = new Alert(LocalDateTime.now(), "NO SPECIFIED", EStatus.ACTIVE);
        //modelMapper.map(alertRequest, alert);

        return alertRepository.save(alert).getId();
    }

    @Override
    public Optional<Alert> getAlertById(Long id) {
        return alertRepository.findById(id);
    }

    @Override
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public void deleteAlert(Long id) {
        if (!alertRepository.existsById(id)) {
            throw new RuntimeException("Alert not found with id: " + id);
        }
        alertRepository.deleteById(id);
    }
}
