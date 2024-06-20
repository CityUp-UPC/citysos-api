package com.citysos.api.incident.domain.service;

import com.citysos.api.incident.domain.entity.Feed;

import java.util.List;
import java.util.Optional;

public interface FeedService {
    List<Feed> fetchAll();
    Optional<Feed> fetchById(int id);
    List<Feed> fetchByIncidentId(int incidentId);
    Feed save(Feed feed);
}
