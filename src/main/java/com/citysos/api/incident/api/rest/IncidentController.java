package com.citysos.api.incident.api.rest;

import com.citysos.api.incident.domain.entity.Incident;
import com.citysos.api.incident.domain.service.IncidentService;
import com.citysos.api.incident.mapping.IncidentMapper;
import com.citysos.api.incident.resources.CreateIncidentResource;
import com.citysos.api.incident.resources.IncidentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/incidents")
@Tag(name = "Incidents", description = "Create, Read, Update and delete incidents entities")
@AllArgsConstructor
public class IncidentController {
    private final IncidentService incidentService;
    //private final CitizenService citizenService;
    private final IncidentMapper mapper;

    @Operation(summary = "Get all registered incidents", responses = {
            @ApiResponse(description = "Successfully fetched all incidents",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping
    public List<Incident> fetchAll() {
        return incidentService.fetchAll();
    }

    @Operation(summary = "Get all incidents by police id", responses = {
            @ApiResponse(description = "Successfully fetched all incidents by police id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping("/police/{policeId}")
    public List<Incident> fetchByPoliceId(@PathVariable Integer policeId) {
        return incidentService.fetchByPoliceId(policeId);
    }

    @Operation(summary = "Get all near incidents by km", responses = {
            @ApiResponse(description = "Successfully fetched near incidents by km",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incident.class)))
    })
    @GetMapping("/near/{km}")
    public List<Incident> fetchByNearCitizen(@RequestParam Double latitude, @RequestParam Double longitude, @PathVariable Integer km) {
        return incidentService.fetchByNearCitizen(latitude, longitude, km);
    }

    @Operation(summary = "Get a incident by id", responses = {
            @ApiResponse(description = "Successfully fetched incident by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping("{id}")
    public IncidentResource fetchById(@PathVariable Integer id) {
        return this.mapper.toResource(incidentService.fetchById(id).get());
    }

    @Operation(summary = "Get all pendients incidents", responses = {
            @ApiResponse(description = "Successfully fetched all pendients incidents",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping("/pendients")
    public List<Incident> fetchAllPendient(){
        return incidentService.fetchAllPendient();
    }

    @Operation(summary = "Get all in progress incidents", responses = {
            @ApiResponse(description = "Successfully fetched all in progress incidents",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping("/in-progress")
    public List<Incident> fetchAllInProgress(){
        return incidentService.fetchAllInProgress();
    }

    @Operation(summary = "Get all help incidents", responses = {
            @ApiResponse(description = "Successfully fetched all help incidents",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping("/help")
    public List<Incident> fetchAllHelp(){
        return incidentService.findIncidentHelp();
    }

    @Operation(summary = "Get all incidents by district", responses = {
            @ApiResponse(description = "Successfully fetched all incidents by district",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping("/district/{district}")
    public List<Incident> fetchByDistrict(@PathVariable String district) {
        return incidentService.fetchByDistrict(district);
    }

    @Operation(summary = "Save an incident", responses = {
            @ApiResponse(description = "Incident successfully created",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @PostMapping
    public ResponseEntity<IncidentResource> save(@RequestBody CreateIncidentResource resource){
        //System.out.println("EL INCIDENT ID ES: " + this.mapper.toModel(resource).getId());
        return new ResponseEntity<>( this.mapper.toResource(incidentService.save(this.mapper.toModel(resource), resource.getCitizenId())), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an incident by id", responses = {
            @ApiResponse(description = "Successfully deleted incident by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(incidentService.deleteById(id)){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Save an incident with Wokwi", responses = {
            @ApiResponse(description = "Incident successfully created",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @PostMapping("/wokwi")
    public ResponseEntity<IncidentResource> saveWokwi(@RequestBody CreateIncidentResource resource){
        return new ResponseEntity<>( this.mapper.toResource(incidentService.save(this.mapper.toModel(resource), resource.getCitizenId())), HttpStatus.CREATED);
    }


    @Operation(summary = "Get pending incidents by citizen id", responses = {
            @ApiResponse(description = "Successfully fetched all pending incidents by citizen id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentResource.class)))
    })
    @GetMapping("/pending/{citizenId}")
    public List<Incident> fetchPendingByCitizenId(@PathVariable Integer citizenId) {
        return incidentService.getPendingIncidentsByCitizenId(citizenId);
    }

}
