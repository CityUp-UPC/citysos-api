package com.citysos.api.incident.mapping;

import com.citysos.api.incident.domain.entity.Incident;
import com.citysos.api.incident.resources.CreateIncidentResource;
import com.citysos.api.incident.resources.IncidentResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class IncidentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public Incident toModel(CreateIncidentResource resource) { return this.mapper.map(resource, Incident.class); }

    public IncidentResource toResource(Incident incident) { return this.mapper.map(incident, IncidentResource.class); }
}
