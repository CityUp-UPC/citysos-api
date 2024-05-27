package com.citysos.api.incident.resources;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.incident.domain.entity.Status;
import com.citysos.api.police.domain.model.entity.Police;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateIncidentResource {

    private  String description;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

    private String address;

    private String district;

    private Integer citizenId; //AL HACERSE EL MAPEO TOMODEL LO TOMA COMO ID, ASI QUE SIEMPRE PONER AL MODELO SetID(NULL)

}
