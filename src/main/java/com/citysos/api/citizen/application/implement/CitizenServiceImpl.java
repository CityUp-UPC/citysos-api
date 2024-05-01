package com.citysos.api.citizen.application.implement;

import com.citysos.api.auth.application.implement.UserDetailsServiceImpl;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import com.citysos.api.citizen.domain.services.CitizenService;
import com.citysos.api.citizen.infrastructure.repositories.CitizenRepository;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;
import com.citysos.api.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    
    @Override
    public Long getCitizenId() {
        return userDetailsService.getCurrentUser().getId();
    }

    @Override
    public Optional<UserEntity> getUserCitizen() {
        return citizenRepository.findById(getCitizenId());
    }

    @Override
    public List<UserEntity> getAllUsersCitizens() {
        return citizenRepository.findAll()
                .stream()
                .filter(x -> x.getRoles().stream().anyMatch(y -> y.getRole().equals(ERole.CITIZEN)))
                .toList();
    }

    @Override
    public void deleteUserCitizen() {
        citizenRepository.deleteById(getCitizenId());
    }

    @Override
    public UserEntity updateUserCitizen(CitizenRequest citizenRequest) {
        UserEntity userCitizen = getUserCitizen()
                .orElseThrow(() -> new ResourceNotFoundException("User citizen not found with id: " + getCitizenId()));
        modelMapper.map(citizenRequest, userCitizen);

        return citizenRepository.save(userCitizen);
    }

    @Override
    public void updatePasswordUserCitizen(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new ValidationException("Passwords do not match");
        }
        UserEntity userCitizen = getUserCitizen()
                .orElseThrow(() -> new ResourceNotFoundException("You have logged in as a citizen"));
        userCitizen.setPassword(passwordEncoder.encode(confirmPassword));

        citizenRepository.save(userCitizen);
    }
}
