package com.citysos.api.citizen.application.implement;

import com.citysos.api.citizen.domain.models.entities.Citizen;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long createCitizen(CitizenRequest citizenRequest) {
        Citizen citizen = new Citizen();
        modelMapper.map(citizenRequest, citizen);

        return citizenRepository.save(citizen).getId();
    }

    @Override
    public Optional<Citizen> getCitizenById(Long id) {
        return citizenRepository.findById(id);
    }

    @Override
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    @Override
    public void deleteCitizenById(Long id) {
        if (!citizenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Citizen not found with id: " + id);
        }
        citizenRepository.deleteById(id);
    }

    @Override
    public Citizen logInCitizen(String email, String password) {
        Citizen citizen = citizenRepository.findByEmailAndPassword(email, password);
        return Optional.ofNullable(citizen).orElseThrow(() -> new RuntimeException("Citizen not found with email: " + email));
    }

    @Override
    public Citizen updateCitizen(Long id, CitizenRequest citizenRequest) {
        Citizen citizenToUpdate = citizenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + id));
        modelMapper.map(citizenRequest, citizenToUpdate);

        return citizenRepository.save(citizenToUpdate);
    }

    @Override
    public void updatePassword(Long id, String password) {
        Citizen citizenToUpdate = citizenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + id));
        citizenToUpdate.setPassword(password);

        citizenRepository.save(citizenToUpdate);
    }
}
