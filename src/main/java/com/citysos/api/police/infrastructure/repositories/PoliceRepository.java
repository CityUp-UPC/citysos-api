package com.citysos.api.police.infrastructure.repositories;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceRepository extends JpaRepository<UserEntity, Long> {

}
