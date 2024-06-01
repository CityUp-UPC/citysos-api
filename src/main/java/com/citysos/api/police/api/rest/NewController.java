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

    @Operation(summary = "Get all registered polices", responses = {
            @ApiResponse(description = "Successfully fetched all polices",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PoliceResource.class)))
    })
    @GetMapping
    public List<New> fetchAll(){return newService.fetchAll();}




}
