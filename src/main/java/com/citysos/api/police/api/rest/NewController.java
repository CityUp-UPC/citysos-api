package com.citysos.api.police.api.rest;

import com.citysos.api.police.domain.model.entity.New;
import com.citysos.api.police.domain.service.NewService;
import com.citysos.api.police.mapping.news.NewMapper;
import com.citysos.api.police.resources.image.ImageResource;
import com.citysos.api.police.resources.news.CreateImageNewResource;
import com.citysos.api.police.resources.news.NewResource;
import com.citysos.api.police.resources.police.PoliceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "News", description = "Create, Read, Update and delete news entities")
@RestController
@RequestMapping("/api/v1/news")
@AllArgsConstructor
public class NewController {


    private final NewService newService;
    private final NewMapper mapper;

    @Operation(summary = "Get all registered news", responses = {
            @ApiResponse(description = "Successfully fetched all news",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewResource.class)))
    })
    @GetMapping
    public List<New> fetchAll(){return newService.fetchAll();}

    @Operation(summary = "Get all news by district", responses = {
            @ApiResponse(description = "Successfully fetched all news by district",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewResource.class)))
    })
    @GetMapping("/district")
    public List<New> fetchByDistrict(@RequestParam String district){return newService.fetchByDistrict(district);}

    @Operation(summary = "Get all news by police Id", responses = {
            @ApiResponse(description = "Successfully fetched all news by police Id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewResource.class)))
    })
    @GetMapping("/policeId")
    public List<New> fetchByPoliceId(@RequestParam Integer policeId){return newService.fetchByPoliceId(policeId);}


    @Operation(summary = "Get a new by id", responses = {
            @ApiResponse(description = "Successfully fetched new by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewResource.class)))
    })
    @GetMapping("{id}")
    public NewResource fetchById(@PathVariable Integer id) {
        return this.mapper.toResource(newService.fetchById(id).get());
    }
}
