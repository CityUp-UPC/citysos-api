package com.citysos.api.police.resources;

import com.citysos.api.auth.domain.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class PoliceResource {
    private Integer id;
    private String assignDistrict;
    private String policeRank;
    private String policeIdentifier;
    private String entityPolice;
    private User user;
}
