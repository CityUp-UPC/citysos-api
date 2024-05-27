package com.citysos.api.citizen.domain.model.persistence;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
}
