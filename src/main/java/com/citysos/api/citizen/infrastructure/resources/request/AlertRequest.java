package com.citysos.api.citizen.infrastructure.resources.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AlertRequest {

    @NotBlank(message = "Type of alert is required")
    private String typeOfAlert;

    @NotBlank(message = "Status is required")
    private String status;
}
