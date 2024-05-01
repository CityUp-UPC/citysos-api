package com.citysos.api.citizen.infrastructure.controllers;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.citizen.domain.services.CitizenService;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;
import com.citysos.api.citizen.infrastructure.resources.response.CitizenResponse;
import com.citysos.api.shared.domain.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('CITIZEN')")
@RequestMapping(value = "/api/v1/citizen", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Citizen", description = "The Citizens API")
public class CitizenController {

    private final CitizenService citizenService;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @GetMapping("")
    public ResponseEntity<CitizenResponse> getCitizen() {
        UserEntity userCitizen = citizenService.getUserCitizen()
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + citizenService.getCitizenId()));
        CitizenResponse citizenResponse = modelMapper.map(userCitizen, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }
    @Transactional(readOnly = true)
    @GetMapping("/all")
    public ResponseEntity<List<CitizenResponse>> getAllCitizens() {
        List<UserEntity> citizens = citizenService.getAllUsersCitizens();
        List<CitizenResponse> citizenResponse = citizens.stream().map(x -> modelMapper.map(x, CitizenResponse.class)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }
    @Transactional
    @DeleteMapping("")
    public ResponseEntity<Void> deleteUserCitizen() {
        citizenService.deleteUserCitizen();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @Transactional
    @PutMapping("/profile")
    public ResponseEntity<CitizenResponse> updateProfile(@Valid @RequestBody CitizenRequest citizenRequest) {
        UserEntity citizenUpdated = citizenService.updateUserCitizen(citizenRequest);
        CitizenResponse citizenResponse = modelMapper.map(citizenUpdated, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }
    @Transactional
    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestParam String newPassword, @RequestParam String confirmPassword) {
        citizenService.updatePasswordUserCitizen(newPassword, confirmPassword);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
