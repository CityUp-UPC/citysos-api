package com.citysos.api.police.resources.news;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/*@Getter
Setter
@With
@NoArgsConstructor
@AllArgsConstructor*/
public class CreateNewResource {
    @NotNull
    private String description;
    private String date;
    @NotNull
    private Integer givenPolice;
}
