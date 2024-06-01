package com.citysos.api.citizen.domain.model.persistence;

import com.citysos.api.citizen.domain.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findByCitizenId(Integer citizenId);

    @Query(nativeQuery = true, value = "SELECT * FROM comments WHERE new_id = :newId")
    Optional<Comment> findByNewId(Integer newId);


}
