package com.citysos.api.police.resources.news;

import com.citysos.api.police.resources.image.ImageResource;
import com.citysos.api.police.resources.police.PoliceResource;
import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class NewResource {
    private Integer id;
    private String description;
    private String district;
    private String date;
}
