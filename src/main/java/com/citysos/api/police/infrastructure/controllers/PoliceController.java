package com.citysos.api.police.infrastructure.controllers;

import com.citysos.api.auth.application.implement.UserDetailsServiceImpl;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.police.domain.services.PoliceService;
import com.citysos.api.police.infrastructure.resources.response.PoliceResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('POLICE')")
@RequestMapping(value = "/api/v1/police", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Police", description = "The Police API")
public class PoliceController {

    private final PoliceService policeService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    public Long getPoliceId() {
        return userDetailsService.getCurrentUser().getId();
    }

    @Transactional(readOnly = true)
    @GetMapping("/all")
    public ResponseEntity<List<PoliceResponse>> getAllPolices() {
        List<UserEntity> polices = policeService.getAllUsersPolices();
        List<PoliceResponse> policeResponse = polices.stream().map(x -> modelMapper.map(x, PoliceResponse.class)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(policeResponse);
    }



}
