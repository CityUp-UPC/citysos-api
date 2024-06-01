package com.citysos.api.citizen.api.rest;

import com.citysos.api.citizen.domain.model.entity.Comment;
import com.citysos.api.citizen.domain.model.service.CommentService;
import com.citysos.api.citizen.mapping.comment.CommentMapper;
import com.citysos.api.citizen.resources.comment.CommentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Comments", description = "Create, Read, Update and delete comments entities")
@RestController
@RequestMapping("api/v1/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    @Operation(summary = "Get all registered comments", responses = {
            @ApiResponse(description = "Successfully fetched all comments",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResource.class)))
    })
    @GetMapping
    public List<Comment> fetchAll() {
        return commentService.fetchAll();
    }

    @Operation(summary = "Get a comment by id", responses = {
            @ApiResponse(description = "Successfully fetched comment by id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResource.class)))
    })
    @GetMapping("/id/{id}")
    public CommentResource fetchById(Integer id){
        return this.mapper.toResource(commentService.fetchById(id).get());
    }

    @Operation(summary = "Get a comment by citizen id", responses = {
            @ApiResponse(description = "Successfully fetched comment by citizen id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResource.class)))
    })
    @GetMapping("/citizen/{citizenId}")
    public CommentResource fetchByCitizenId(Integer citizenId){
        return this.mapper.toResource(commentService.fetchByCitizenId(citizenId).get());
    }

    @Operation(summary = "Get a comment by new id", responses = {
            @ApiResponse(description = "Successfully fetched comment by new id",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResource.class)))
    })
    @GetMapping("/news/{newId}")
    public CommentResource fetchByNewId(Integer newId){
        return this.mapper.toResource(commentService.fetchByNewId(newId).get());
    }
}
