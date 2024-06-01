package com.citysos.api.citizen.service;

import com.citysos.api.citizen.domain.model.entity.Comment;
import com.citysos.api.citizen.domain.model.persistence.CommentRepository;
import com.citysos.api.citizen.domain.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }

    @Override
    public List<Comment> fetchAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> fetchById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    public Optional<Comment> fetchByCitizenId(Integer citizenId) {
        return commentRepository.findByCitizenId(citizenId);
    }

    @Override
    public Optional<Comment> fetchByNewId(Integer newId) {
        return commentRepository.findByNewId(newId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }


    @Override
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }


}
