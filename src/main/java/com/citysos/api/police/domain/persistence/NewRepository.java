package com.citysos.api.police.domain.persistence;

import com.citysos.api.police.domain.model.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewRepository extends JpaRepository<New, Integer> {
    List<New> findByDistrict(String district);
}
