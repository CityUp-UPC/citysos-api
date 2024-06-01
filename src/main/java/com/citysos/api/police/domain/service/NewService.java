package com.citysos.api.police.domain.service;

import com.citysos.api.police.domain.model.entity.New;

import java.util.List;
import java.util.Optional;

public interface NewService {
    List<New> fetchAll();
    List<New> fetchByDistrict(String district);
    List<New> fetchByPoliceId(Integer policeId);
    Optional<New> fetchById(Integer id);

    New save(New newEntity);
    Optional<New> update(New newEntity);

    boolean delete(Integer id);
}
