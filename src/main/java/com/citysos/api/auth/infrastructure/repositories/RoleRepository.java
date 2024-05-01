package com.citysos.api.auth.infrastructure.repositories;

import com.citysos.api.auth.domain.models.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
