package com.citysos.api.incident.api.rest;
import com.citysos.api.incident.domain.entity.Feed;
import com.citysos.api.incident.domain.service.FeedService;
import com.citysos.api.incident.mapping.FeedMapper;
import com.citysos.api.incident.resources.FeedResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/feeds")
@Tag(name = "Feeds", description = "Create, Read, Update and delete feeds entities")
@AllArgsConstructor
public class FeedController {
    private FeedService feedService;
    private FeedMapper feedMapper;

    @GetMapping
    public List<Feed> getAllFeeds() {
        return feedService.fetchAll();
    }

    @GetMapping("/{id}")
    public FeedResource getFeedById(@PathVariable int id) {
        Optional<Feed> feed = feedService.fetchById(id);
        return feed.map(feedMapper::toResource)
                .orElse(null);
    }

    @GetMapping("/incident/{incidentId}")
    public List<Feed> getFeedsByIncidentId(@PathVariable int incidentId) {
        return feedService.fetchByIncidentId(incidentId);
    }
}
