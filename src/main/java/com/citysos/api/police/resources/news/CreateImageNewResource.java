package com.citysos.api.police.resources.news;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateImageNewResource {
    @NotNull
    private String description;

    private List<MultipartFile> files;
}

