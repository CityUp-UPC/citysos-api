package com.citysos.api.auth.infrastructure.repositories;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.role = ?1")
    List<UserEntity> findUsersByRole(ERole role);
}
