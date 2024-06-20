package com.citysos.api.incident.domain.persistence;

import com.citysos.api.incident.domain.entity.Feed;
import com.citysos.api.incident.domain.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Integer> {
    @Query(value = "SELECT * FROM feeds WHERE incident_id = :givenIncident", nativeQuery = true)
    List<Feed> findByGivenIncident(int givenIncident);
}
