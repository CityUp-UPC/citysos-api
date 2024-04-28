package com.citysos.api.citizen.infrastructure.resources.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AlertRequest {

    @NotBlank(message = "Type of alert is required")
    private String typeOfAlert;

    @NotBlank(message = "Status is required")
    private String status;
}
