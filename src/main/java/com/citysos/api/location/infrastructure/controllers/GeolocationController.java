package com.citysos.api.location.infrastructure.controllers;

import com.citysos.api.location.domain.models.Geolocation;
import com.citysos.api.location.domain.services.GeolocationService;
import com.citysos.api.location.infrastructure.resources.GeolocationRequest;
import com.citysos.api.location.infrastructure.resources.GeolocationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/geolocation", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Geolocations", description = "The Geolocations API")
public class GeolocationController {
    private final GeolocationService geolocationService;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<GeolocationResponse> getById(@PathVariable Long id) {
        Geolocation geolocation = geolocationService.getGeoLocationById(id)
                .orElseThrow(() -> new RuntimeException("Geolocation not found with id: " + id));
        GeolocationResponse geolocationResponse = modelMapper.map(geolocation, GeolocationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(geolocationResponse);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<GeolocationResponse> create(@RequestBody GeolocationRequest request) {
        Long id = geolocationService.createGeolocation(request);
        Geolocation geolocation = geolocationService.getGeoLocationById(id)
                .orElseThrow(() -> new RuntimeException("Geolocation not found with id: " + id));
        GeolocationResponse response = modelMapper.map(geolocation, GeolocationResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
