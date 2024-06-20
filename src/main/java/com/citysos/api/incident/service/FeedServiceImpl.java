package com.citysos.api.incident.service;

import com.citysos.api.incident.domain.entity.Feed;
import com.citysos.api.incident.domain.persistence.FeedRepository;
import com.citysos.api.incident.domain.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    FeedRepository feedRepository;

    public FeedServiceImpl(FeedRepository feedRepository){
        this.feedRepository = feedRepository;
    }
    @Override
    public List<Feed> fetchAll() {
        return feedRepository.findAll();
    }

    @Override
    public Optional<Feed> fetchById(int id) {
        return feedRepository.findById(id);
    }

    @Override
    public List<Feed> fetchByIncidentId(int incidentId) {
        return feedRepository.findByGivenIncident(incidentId);
    }

    @Override
    public Feed save(Feed feed) {
        return feedRepository.save(feed);
    }
}
