package com.citysos.api.police.mapping.image;

import com.citysos.api.police.domain.model.entity.Image;
import com.citysos.api.police.resources.image.CreateImageResource;
import com.citysos.api.police.resources.image.ImageResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ImageMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public Image toModel(CreateImageResource resource){ return this.mapper.map(resource, Image.class); }
    public ImageResource toResource(Image image){ return this.mapper.map(image, ImageResource.class);}
}
