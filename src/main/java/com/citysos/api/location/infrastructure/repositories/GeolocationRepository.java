package com.citysos.api.location.infrastructure.repositories;

import com.citysos.api.location.domain.models.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {
}
