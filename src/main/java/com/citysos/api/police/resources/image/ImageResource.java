package com.citysos.api.police.resources.image;

import com.citysos.api.police.domain.model.entity.New;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResource {
    private Integer id;
    private String url;
    @JsonIgnore
    private New news;

}
