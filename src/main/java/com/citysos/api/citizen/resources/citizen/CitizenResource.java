package com.citysos.api.citizen.resources.citizen;

import com.citysos.api.auth.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitizenResource {
    private Integer id;
    private String district;
    private User user;
}
