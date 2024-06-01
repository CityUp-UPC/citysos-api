package com.citysos.api.police.mapping.news;

import com.citysos.api.police.domain.model.entity.New;
import com.citysos.api.police.resources.news.CreateNewResource;
import com.citysos.api.police.resources.news.NewResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class NewMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public New toModel(CreateNewResource resource){ return this.mapper.map(resource, New.class); }

    public NewResource toResource(New news){return this.mapper.map(news, NewResource.class);}
}
