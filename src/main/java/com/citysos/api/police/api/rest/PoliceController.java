package com.citysos.api.police.api.rest;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.incident.domain.entity.Feed;
import com.citysos.api.incident.domain.service.FeedService;
import com.citysos.api.incident.domain.service.IncidentService;
import com.citysos.api.incident.mapping.FeedMapper;
import com.citysos.api.incident.mapping.IncidentMapper;
import com.citysos.api.incident.resources.CreateFeedResource;
import com.citysos.api.incident.resources.RequestFeedResource;
import com.citysos.api.police.domain.model.entity.Image;
import com.citysos.api.police.domain.model.entity.New;
import com.citysos.api.police.domain.model.entity.Police;
import com.citysos.api.police.domain.service.ImageService;
import com.citysos.api.police.domain.service.NewService;
import com.citysos.api.police.domain.service.PoliceService;
import com.citysos.api.police.mapping.image.ImageMapper;
import com.citysos.api.police.mapping.news.NewMapper;
import com.citysos.api.police.mapping.police.PoliceMapper;
import com.citysos.api.police.resources.news.CreateImageNewResource;
import com.citysos.api.police.resources.news.CreateNewResource;
import com.citysos.api.police.resources.news.NewResource;
import com.citysos.api.police.resources.police.CreatePoliceResource;
import com.citysos.api.police.resources.police.PoliceResource;
import com.citysos.api.police.resources.police.RequestJoinIncident;
import com.citysos.api.police.resources.police.UpdateLocationRequest;
import com.citysos.api.shared.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Polices", description = "Create, Read, Update and delete polices entities")
@RestController
@RequestMapping("api/v1/polices")
@AllArgsConstructor
public class PoliceController {
    private final PoliceService policeService;
    private final ImageService imageService;
    private final IncidentService incidentService;
    private final NewService newService;
    private final FeedService feedService;
    private final PoliceMapper mapper;
    private final ImageMapper imageMapper;
    private final IncidentMapper incidentMapper;
    private final NewMapper newMapper;
    private final FeedMapper feedMapper;

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
    @PutMapping("/incident/{incidentId}")
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


    @Operation(summary = "Update location of a police", responses = {
            @ApiResponse(description = "Location successfully updated",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("{id}/location")
    public ResponseEntity<?> updateLocation(@PathVariable Integer id, @RequestBody UpdateLocationRequest request) {
        policeService.updateLocation(id, request.getLatitude(), request.getLongitude());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update in service status of a police", responses = {
            @ApiResponse(description = "In service status successfully updated",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("{id}")
    public ResponseEntity<?> updateInService(@PathVariable Integer id) {
        policeService.updateInService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Create a new", responses = {
            @ApiResponse(description = "Successfully created new",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Feed.class)))
    })
    @PostMapping("{policeId}/news")
    public New saveNew(@RequestParam("description") String description,
                       @RequestParam("files") List<MultipartFile> files,
                       @RequestParam("district") String district,
                       @PathVariable Integer policeId) {

        Optional<Police> policeOptional = policeService.fetchById(policeId);
        if (!policeOptional.isPresent()) {
            throw new RuntimeException("Police not found");
        }
        Police police = policeOptional.get();

        CreateNewResource aux = new CreateNewResource();
        aux.setDescription(description);
        aux.setGivenPolice(police);
        aux.setDistrict(district);

        New newSaved = newService.save(this.newMapper.toModel(aux));

        List<Image> images = files.stream().map(file -> {
            String imageUrl = imageService.uploadImage(file);
            Image image = new Image();
            image.setUrl(imageUrl);
            image.setNews(newSaved);
            return imageService.save(image);
        }).collect(Collectors.toList());

        // Asociar imágenes a la entidad New y guardar
        newSaved.setImages(images);
        newService.update(newSaved);

        return newSaved;
    }

    @Operation(summary = "Get a police by user id", responses = {
            @ApiResponse(description = "Successfully fetched citizen by ser id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Police.class)))
    })
    @GetMapping("user/{id}")
    public Police fetchByUserId(@PathVariable Integer id) {
        return policeService.fetchByUserId(id);
    }


    @Operation(summary = "Get all feeds by incident id", responses = {
            @ApiResponse(description = "Successfully fetched all feeds by incident id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Feed.class)))
    })
    //@PostMapping("incident-feed")
    @PostMapping("{id}/incident/{incidentId}/feed")
    public Feed feedIncident(@PathVariable Integer id, @PathVariable Integer incidentId, @RequestBody RequestFeedResource resource){
        //public Feed feedIncident(@RequestBody CreateFeedResource resource){
        CreateFeedResource aux = new CreateFeedResource();
        aux.setComment(resource.getComment());
        aux.setGivenIncident(incidentService.fetchById(incidentId).orElseThrow(() -> new CustomException("Incident not found", HttpStatus.NOT_FOUND)));
        aux.setGivenPolice(policeService.fetchById(id).orElseThrow(() -> new CustomException("Police not found", HttpStatus.NOT_FOUND)));
        return feedService.save(this.feedMapper.toModel(aux));
    }

}
