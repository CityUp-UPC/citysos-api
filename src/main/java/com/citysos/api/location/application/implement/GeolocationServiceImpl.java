package com.citysos.api.location.application.implement;

import com.citysos.api.location.domain.models.Geolocation;
import com.citysos.api.location.domain.services.GeolocationService;
import com.citysos.api.location.infrastructure.repositories.GeolocationRepository;
import com.citysos.api.location.infrastructure.resources.GeolocationRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeolocationServiceImpl implements GeolocationService {

    private final GeolocationRepository geolocationRepository;

    private final ModelMapper modelMapper;

    @Override
    public Optional<Geolocation> getGeoLocationById(Long id) {
        return geolocationRepository.findById(id)
                .map(geolocationEntity -> modelMapper.map(geolocationEntity, Geolocation.class));
    }

    @Override
    public Long createGeolocation(GeolocationRequest geolocationRequest) {
        Geolocation geolocation = new Geolocation(
                geolocationRequest.getLatitude(),
                geolocationRequest.getLongitude()
        );

        modelMapper.map(geolocationRequest, geolocation);

        return geolocationRepository.save(geolocation).getId();
    }

    @Override
    public List<Geolocation> getAllGeoLocations() {
        return geolocationRepository.findAll()
                .stream()
                .map(geolocationEntity -> modelMapper.map(geolocationEntity, Geolocation.class))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteGeoLocation() {
        geolocationRepository.deleteAll();
    }
}
