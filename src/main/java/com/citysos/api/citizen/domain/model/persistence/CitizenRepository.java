package com.citysos.api.citizen.domain.model.persistence;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.citizen.resources.citizen.CitizenResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM citizens WHERE user_id = :userId")
    Citizen findByUserId(Integer userId);
}
