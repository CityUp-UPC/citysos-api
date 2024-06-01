package com.citysos.api.citizen.mapping.citizen;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.citizen.resources.citizen.CitizenResource;
import com.citysos.api.citizen.resources.citizen.CreateCitizenResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CitizenMapper {
    @Autowired
    EnhancedModelMapper mapper;
    public Citizen toModel(CreateCitizenResource resource){ return this.mapper.map(resource, Citizen.class); }

    public CitizenResource toResource(Citizen citizen){ return this.mapper.map(citizen, CitizenResource.class); }
}
