package com.citysos.api.citizen.infrastructure.repositories;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CitizenRepository extends JpaRepository<UserEntity, Long> {
}
