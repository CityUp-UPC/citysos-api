package com.citysos.api.police.api.rest;
        
import com.citysos.api.police.domain.service.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Images", description = "Create, Read, Update and delete images entities")
@RestController
@RequestMapping("/api/v1/images")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = imageService.uploadImage(file);
        if (imageUrl.equals("Error uploading image")) {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
        return ResponseEntity.ok(imageUrl);
    }
}
