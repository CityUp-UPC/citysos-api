package com.citysos.api.citizen.infrastructure.controllers;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.citizen.domain.services.CitizenService;
import com.citysos.api.citizen.infrastructure.resources.request.CitizenRequest;
import com.citysos.api.citizen.infrastructure.resources.response.CitizenResponse;
import com.citysos.api.shared.domain.exceptions.ResourceNotFoundException;
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
@RequestMapping(value = "api/v1/citizen", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Citizen", description = "The Citizens API")
public class CitizenController {
    private final CitizenService citizenService;
    private final ModelMapper modelMapper;
    public CitizenController(CitizenService citizenService, ModelMapper modelMapper) {
        this.citizenService = citizenService;
        this.modelMapper = modelMapper;
    }

    @Transactional//READY...!!!
    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponse> getCitizenById(@PathVariable Long id) {
        UserEntity userCitizen = citizenService.getUserCitizenById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + id));
        CitizenResponse citizenResponse = modelMapper.map(userCitizen, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }
    @Transactional(readOnly = true)//READY...!!!
    @GetMapping("/all")
    public ResponseEntity<List<CitizenResponse>> getAllCitizens() {
        List<UserEntity> citizens = citizenService.getAllUsersCitizens();
        List<CitizenResponse> citizenResponse = citizens.stream().map(x -> modelMapper.map(x, CitizenResponse.class)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }
    @Transactional//READY...!!!
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCitizenById(@PathVariable Long id) {
        citizenService.deleteUserCitizenById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @Transactional//READY...!!!
    @PutMapping("/{id}/update-profile")
    public ResponseEntity<CitizenResponse> updateProfile(@PathVariable Long id, @Valid @RequestBody CitizenRequest citizenRequest) {
        UserEntity citizenUpdated = citizenService.updateUserCitizen(id, citizenRequest);
        CitizenResponse citizenResponse = modelMapper.map(citizenUpdated, CitizenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(citizenResponse);
    }
    @Transactional//FALTA IMPLEMENTAR
    //@PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestParam String password) {
        citizenService.updatePasswordUserCitizen(id, password);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
