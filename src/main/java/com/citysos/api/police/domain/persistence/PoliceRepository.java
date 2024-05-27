package com.citysos.api.police.domain.persistence;

import com.citysos.api.police.domain.model.entity.Police;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliceRepository extends JpaRepository<Police, Integer> {

}
