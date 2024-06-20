package com.citysos.api.incident.mapping;

import com.citysos.api.incident.domain.entity.Feed;
import com.citysos.api.incident.domain.entity.Incident;
import com.citysos.api.incident.resources.CreateFeedResource;
import com.citysos.api.incident.resources.CreateIncidentResource;
import com.citysos.api.incident.resources.FeedResource;
import com.citysos.api.incident.resources.IncidentResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class FeedMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public Feed toModel(CreateFeedResource resource) { return this.mapper.map(resource, Feed.class); }

    public FeedResource toResource(Feed feed) { return this.mapper.map(feed, FeedResource.class); }
}
