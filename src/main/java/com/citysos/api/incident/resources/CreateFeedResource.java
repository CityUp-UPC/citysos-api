package com.citysos.api.incident.resources;
import com.citysos.api.incident.domain.entity.Incident;
import com.citysos.api.police.domain.model.entity.Police;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFeedResource {
    private String comment;
    private Police givenPolice;
    private Incident givenIncident;
}
