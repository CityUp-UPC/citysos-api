package com.citysos.api.citizen.mapping.comment;

import com.citysos.api.citizen.domain.model.entity.Comment;
import com.citysos.api.citizen.resources.comment.CommentResource;
import com.citysos.api.citizen.resources.comment.CreateCommentResource;
import com.citysos.api.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class CommentMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Comment toModel(CreateCommentResource resource){ return this.mapper.map(resource, Comment.class); }

    public CommentResource toResource(Comment comment){ return this.mapper.map(comment, CommentResource.class);}


}
