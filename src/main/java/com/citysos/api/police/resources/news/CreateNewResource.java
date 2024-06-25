package com.citysos.api.police.resources.news;

import com.citysos.api.police.domain.model.entity.Police;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewResource {
    @NotNull
    private String description;
    @NotNull
    private Police givenPolice;
    private String district;
}
