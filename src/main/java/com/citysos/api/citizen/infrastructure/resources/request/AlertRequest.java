package com.citysos.api.citizen.infrastructure.resources.request;

import com.citysos.api.citizen.domain.models.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Instant;

@NoArgsConstructor
@Getter
public class AlertRequest {

    private String typeOfAlert;
}
