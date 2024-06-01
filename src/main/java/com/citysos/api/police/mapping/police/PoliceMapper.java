package com.citysos.api.police.mapping.police;

import com.citysos.api.police.domain.model.entity.Police;
import com.citysos.api.police.resources.police.CreatePoliceResource;
import com.citysos.api.police.resources.police.PoliceResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class PoliceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public Police toModel(CreatePoliceResource resource){ return this.mapper.map(resource, Police.class); }

    public PoliceResource toResource(Police police){ return this.mapper.map(police, PoliceResource.class);}
}
