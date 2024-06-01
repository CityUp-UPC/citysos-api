package com.citysos.api.police.resources.image;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateImageResource {
    @NotNull
    private String url;
    @NotNull
    private Integer givenNew;
}
