package com.citysos.api.location.infrastructure.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GeolocationResponse {
    private Double latitude;
    private Double longitude;
}
