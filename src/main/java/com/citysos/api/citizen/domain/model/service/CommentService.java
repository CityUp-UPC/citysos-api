package com.citysos.api.citizen.domain.model.service;

import com.citysos.api.citizen.domain.model.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> fetchAll();
    Optional<Comment> fetchById(Integer id);
    Optional<Comment> fetchByCitizenId(Integer citizenId);
    Optional<Comment> fetchByNewId(Integer newId);
    Comment save(Comment comment);
    void deleteById(Integer id);
    // void update(Comment comment);
}
