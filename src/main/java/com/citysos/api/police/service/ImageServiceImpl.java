package com.citysos.api.police.service;

import com.citysos.api.police.domain.model.entity.Image;
import com.citysos.api.police.domain.persistence.ImageRepository;
import com.citysos.api.police.domain.persistence.NewRepository;
import com.citysos.api.police.domain.service.ImageService;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private StorageClient storageClient;

    public ImageServiceImpl(ImageRepository imageRepository, NewRepository newRepository){
        this.imageRepository = imageRepository;
        this.newRepository = newRepository;
    }


    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            storageClient.bucket().create(fileName, file.getBytes(), file.getContentType());
            return storageClient.bucket().get(fileName).getMediaLink();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image";
        }
    }

    @Override
    public List<Image> findImageByNewsId(Integer newsId) {
        return imageRepository.findImageByNewsId(newsId);
    }

    @Override
    public Optional<Image> findImageById(Integer id) {
        return imageRepository.findById(id);
    }

}
