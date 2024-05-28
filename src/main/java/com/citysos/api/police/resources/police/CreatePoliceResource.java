package com.citysos.api.police.resources.police;

import com.citysos.api.auth.domain.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreatePoliceResource {
    @NotNull
    private String assignDistrict;

    private String policeRank;
    @NotNull
    private String policeIdentifier;

    @NotNull
    private String entityPolice;

    @NotNull
    private User user;
}
