package com.citysos.api.citizen.infrastructure.controllers;

import com.citysos.api.citizen.domain.models.entities.Citizen;
import com.citysos.api.citizen.domain.services.CitizenService;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;
import com.citysos.api.citizen.infrastructure.resources.response.CitizenResponse;
import com.citysos.api.shared.domain.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping(value = "api/v1/citizens", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Citizen", description = "The Citizens API")
public class CitizenController {
    private final CitizenService citizenService;
    private final ModelMapper modelMapper;
    public CitizenController(CitizenService citizenService, ModelMapper modelMapper) {
        this.citizenService = citizenService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @PostMapping("/sign-up")
    public ResponseEntity<CitizenResponse> citizenSignUp(@Valid @RequestBody CitizenRequest citizenRequest) {
        Long id = citizenService.createCitizen(citizenRequest);
        Citizen citizenCreated = citizenService.getCitizenById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + id));
        CitizenResponse citizenResponse = modelMapper.map(citizenCreated, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(citizenResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponse> getById(@PathVariable Long id) {
        Citizen citizen = citizenService.getCitizenById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + id));
        CitizenResponse citizenResponse = modelMapper.map(citizen, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping("/all")
    public ResponseEntity<List<CitizenResponse>> getAll() {
        List<Citizen> citizens = citizenService.getAllCitizens();
        List<CitizenResponse> citizenResponse = citizens.stream().map(x -> modelMapper.map(x, CitizenResponse.class)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }

    @Transactional
    @PutMapping("/{id}/update-profile")
    public ResponseEntity<CitizenResponse> updateProfile(@PathVariable Long id, @Valid @RequestBody CitizenRequest citizenRequest) {
        Citizen citizenUpdated = citizenService.updateCitizen(id, citizenRequest);
        CitizenResponse citizenResponse = modelMapper.map(citizenUpdated, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        citizenService.deleteCitizenById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @PostMapping("/log-in")
    public ResponseEntity<CitizenResponse> logIn(@RequestParam String email, @RequestParam String password) {
        Citizen citizen = citizenService.logInCitizen(email, password);
        CitizenResponse citizenResponse = modelMapper.map(citizen, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }

    @Transactional
    @PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestParam String password) {
        citizenService.updatePassword(id, password);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
