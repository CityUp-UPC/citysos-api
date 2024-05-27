package com.citysos.api.incident.resources;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.incident.domain.entity.Status;
import com.citysos.api.police.domain.model.entity.Police;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentResource {
    private Integer id;

    private  String description;

    private LocalDateTime date;

    private String latitude;

    private String longitude;

    private String address;

    private String district;

    private Status status;

    private Citizen citizen;

    private List<Police> police;
}
