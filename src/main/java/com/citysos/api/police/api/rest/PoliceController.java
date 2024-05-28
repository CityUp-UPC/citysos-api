package com.citysos.api.police.api.rest;

import com.citysos.api.police.domain.model.entity.Police;
import com.citysos.api.police.domain.service.PoliceService;
import com.citysos.api.police.mapping.PoliceMapper;
import com.citysos.api.police.resources.police.CreatePoliceResource;
import com.citysos.api.police.resources.police.PoliceResource;
import com.citysos.api.police.resources.police.RequestJoinIncident;
import com.citysos.api.police.resources.police.UpdateLocationRequest;
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

@Tag(name = "Polices", description = "Create, Read, Update and delete polices entities")
@RestController
@RequestMapping("api/v1/polices")
@AllArgsConstructor
public class PoliceController {
    private final PoliceService policeService;
    private final PoliceMapper mapper;

    @Operation(summary = "Get all registered polices", responses = {
            @ApiResponse(description = "Successfully fetched all polices",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PoliceResource.class)))
    })
    @GetMapping
    public List<Police> fetchAll() {
        return policeService.fetchAll();
    }

    @Operation(summary = "Get a police by id", responses = {
            @ApiResponse(description = "Successfully fetched police by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PoliceResource.class)))
    })
    @GetMapping("{id}")
    public PoliceResource fetchById(@PathVariable Integer id) {
        return this.mapper.toResource(policeService.fetchById(id).get());
    }

    @Operation(summary = "Save a police", responses = {
            @ApiResponse(description = "Police successfully created",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PoliceResource.class)))
    })
    @PostMapping
    public PoliceResource save(@RequestBody CreatePoliceResource resource){
        return this.mapper.toResource(policeService.save(this.mapper.toModel(resource)));
    }

    @Operation(summary = "Join an incident", responses = {
            @ApiResponse(description = "Successful subscription",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PoliceResource.class)))
    })
    @PostMapping("{id}/incident")
    public ResponseEntity<?> joinIncident(@PathVariable Integer id, @RequestBody RequestJoinIncident request) {
        policeService.joinIncident(id, request.getIncidentId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Complete an incident", responses = {
            @ApiResponse(description = "Incident successfully completed",
                    responseCode = "202",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/incident/{incidentId}")
    public ResponseEntity<?> completedIncident(@PathVariable Integer incidentId){
        policeService.completedIncident(incidentId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Request reinforcements for an incident", responses = {
            @ApiResponse(description = "Reinforcements successfully requested for the incident",
                    responseCode = "202",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/incident/{incidentId}/help")
    public ResponseEntity<?> requestReinforcements(@PathVariable Integer incidentId){
        policeService.requestReinforcements(incidentId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("{id}/location")
    public ResponseEntity<?> updateLocation(@PathVariable Integer id, @RequestBody UpdateLocationRequest request) {
        policeService.updateLocation(id, request.getLatitude(), request.getLongitude());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateInService(@PathVariable Integer id) {
        policeService.updateInService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
