package com.citysos.api.police.domain.persistence;

import com.citysos.api.police.domain.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM images WHERE new_id = :newId")
    List<Image> findImageByNewsId(Integer newId);
}
