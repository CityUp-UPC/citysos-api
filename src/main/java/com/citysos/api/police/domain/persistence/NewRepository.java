package com.citysos.api.police.domain.persistence;

import com.citysos.api.police.domain.model.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewRepository extends JpaRepository<New, Integer> {
    List<New> findByDistrict(String district);

    @Query(nativeQuery = true, value = "SELECT * FROM news WHERE police_id = :policeId")
    List<New> findByPoliceId(Integer policeId);
}
