package com.citysos.api.citizen.infrastructure.resources.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AlertResponse {

    private Long id;

    private Date date;

    private String typeOfAlert;

    private String status;
}
