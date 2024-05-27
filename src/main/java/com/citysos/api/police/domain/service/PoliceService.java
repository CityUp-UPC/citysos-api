package com.citysos.api.police.domain.service;

import com.citysos.api.police.domain.model.entity.Police;

import java.util.List;
import java.util.Optional;

public interface PoliceService {
    List<Police> fetchAll();

    Optional<Police> fetchById(Integer id);

    Police save(Police police);

    void joinIncident(Integer id, Integer incidentId);

    void completedIncident(Integer incidentId);

    void requestReinforcements(Integer incidentId);
}