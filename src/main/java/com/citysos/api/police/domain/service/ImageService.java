package com.citysos.api.police.domain.service;

import com.citysos.api.police.domain.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image save(Image image);
    String uploadImage(MultipartFile multipartFile);
    List<Image> findImageByNewsId(Integer newsId);
    Optional<Image> findImageById(Integer id);
}
