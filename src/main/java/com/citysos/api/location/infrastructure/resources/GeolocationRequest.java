package com.citysos.api.location.infrastructure.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GeolocationRequest {

    private Double latitude;
    private Double longitude;

}
