package com.citysos.api.citizen.api.rest;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.citizen.domain.model.service.CitizenService;
import com.citysos.api.citizen.mapping.CitizenMapper;
import com.citysos.api.citizen.resources.CitizenResource;
import com.citysos.api.incident.domain.service.IncidentService;
import com.citysos.api.police.resources.police.PoliceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Citizens", description = "Create, Read, Update and delete citizens entities")
@RestController
@RequestMapping("api/v1/citizens")
@AllArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;
    private final IncidentService incidentService;
    private final CitizenMapper mapper;

    @Operation(summary = "Get all registered citizens", responses = {
            @ApiResponse(description = "Successfully fetched all citizens",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PoliceResource.class)))
    })
    @GetMapping
    public List<Citizen> fetchAll() {
        return citizenService.fetchAll();
    }

    @GetMapping("{id}/incidents")
    public List<Optional<?>> fetchIncidentsByCitizenId(@PathVariable Integer id){
        return incidentService.fetchByCitizenId(id);
    }

    @Operation(summary = "Get a citizen by id", responses = {
            @ApiResponse(description = "Successfully fetched citizen by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PoliceResource.class)))
    })
    @GetMapping("{id}")
    public CitizenResource fetchById(@PathVariable Integer id) {
        return this.mapper.toResource(citizenService.fetchById(id).get());
    }
}
