package com.citysos.api.citizen.infrastructure.repositories;

import com.citysos.api.citizen.domain.models.aggregates.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
