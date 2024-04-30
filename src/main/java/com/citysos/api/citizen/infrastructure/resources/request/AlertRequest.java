package com.citysos.api.citizen.infrastructure.resources.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AlertRequest {

    @Schema(description = "Type of alert", example = "SOS")
    private String typeOfAlert;
}
