package com.citysos.api.citizen.mapping;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.citizen.resources.CitizenResource;
import com.citysos.api.citizen.resources.CreateCitizenResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CitizenMapper {
    @Autowired
    EnhancedModelMapper mapper;
    public Citizen toModel(CreateCitizenResource resource){ return this.mapper.map(resource, Citizen.class); }

    public CitizenResource toResource(Citizen citizen){ return this.mapper.map(citizen, CitizenResource.class); }
}
