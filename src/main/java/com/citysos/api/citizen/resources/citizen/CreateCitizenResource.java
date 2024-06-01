package com.citysos.api.citizen.resources.citizen;

import com.citysos.api.auth.domain.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCitizenResource {
    private String district;
    @NotNull
    private User user;
}
