package com.citysos.api.police.service;

import com.citysos.api.police.domain.model.entity.Image;
import com.citysos.api.police.domain.persistence.ImageRepository;
import com.citysos.api.police.domain.persistence.NewRepository;
import com.citysos.api.police.domain.service.ImageService;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
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
    private Storage storage;

    public ImageServiceImpl(ImageRepository imageRepository, NewRepository newRepository) {
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
            BlobId blobId = BlobId.of("citysos-api.appspot.com", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();

            Blob blob = storage.create(blobInfo, file.getBytes());

            // Verificar si el archivo se ha subido correctamente
            if (blob == null) {
                return "Error uploading image";
            }

            blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

            return String.format("https://storage.googleapis.com/%s/%s", "citysos-api.appspot.com", fileName);
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
