package com.citysos.api.citizen.application.implement;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import com.citysos.api.citizen.domain.services.CitizenService;
import com.citysos.api.citizen.infrastructure.repositories.CitizenRepository;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;
import com.citysos.api.shared.domain.exceptions.ResourceNotFoundException;
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
    private final PasswordEncoder passwordEncoder;//Implement ID of passwordEncoder


    @Override
    public Optional<UserEntity> getUserCitizenById(Long id) {
        return citizenRepository.findById(id)
                .filter(x -> x.getRoles().stream().anyMatch(y -> y.getRole().equals(ERole.CITIZEN)));
    }

    @Override
    public List<UserEntity> getAllUsersCitizens() {
        return citizenRepository.findAll()
                .stream()
                .filter(x -> x.getRoles().stream().anyMatch(y -> y.getRole().equals(ERole.CITIZEN)))
                .toList();
    }

    @Override
    public void deleteUserCitizenById(Long id) {
        if (getAllUsersCitizens().stream().noneMatch(x -> x.getId().equals(id))) {
            throw new ResourceNotFoundException("User citizen not found with id: " + id);
        }
        citizenRepository.deleteById(id);
    }

    @Override
    public UserEntity updateUserCitizen(Long id, CitizenRequest citizenRequest) {
        UserEntity userCitizen = getUserCitizenById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User citizen not found with id: " + id));
        modelMapper.map(citizenRequest, userCitizen);

        return citizenRepository.save(userCitizen);
    }

    @Override
    public void updatePasswordUserCitizen(Long id, String password) {

    }
}
