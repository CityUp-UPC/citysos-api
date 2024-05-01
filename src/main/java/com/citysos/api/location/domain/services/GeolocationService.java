package com.citysos.api.location.domain.services;

import com.citysos.api.location.domain.models.Geolocation;
import com.citysos.api.location.infrastructure.resources.GeolocationRequest;

import java.util.List;
import java.util.Optional;

public interface GeolocationService {

    Long createGeolocation(GeolocationRequest geolocationRequest);
    Optional<Geolocation> getGeoLocationById(Long id);
    List<Geolocation> getAllGeoLocations();
    void deleteGeoLocation();

}
