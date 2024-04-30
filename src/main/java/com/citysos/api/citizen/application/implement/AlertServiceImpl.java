package com.citysos.api.citizen.application.implement;

import com.citysos.api.auth.application.implement.UserDetailsServiceImpl;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.infrastructure.repositories.UserRepository;
import com.citysos.api.citizen.domain.models.aggregates.Alert;
import com.citysos.api.citizen.domain.models.enums.EStatus;
import com.citysos.api.citizen.domain.services.AlertService;
import com.citysos.api.citizen.infrastructure.repositories.AlertRepository;
import com.citysos.api.citizen.infrastructure.resources.request.AlertRequest;
import com.citysos.api.shared.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final ModelMapper modelMapper;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public Long createAlert(AlertRequest alertRequest) {
        UserEntity currentUser = getCurrentUser();
        Alert alert = new Alert(currentUser, Instant.now(), alertRequest.getTypeOfAlert(), EStatus.ACTIVE);
        modelMapper.map(alertRequest, alert);

        return alertRepository.save(alert).getId();
    }
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetailsService.findUser(userDetails.getUsername());
        }
        else {
            return null;
        }
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
        Alert alertToDelete = alertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found with id: " + id));
        alertToDelete.setStatus(EStatus.CANCELLED);

        alertRepository.save(alertToDelete);
    }
}
