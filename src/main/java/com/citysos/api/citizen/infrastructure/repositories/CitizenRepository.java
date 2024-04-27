package com.citysos.api.citizen.infrastructure.repositories;

import com.citysos.api.citizen.domain.models.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    Citizen findByEmailAndPassword(String email, String password);
}
