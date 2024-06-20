package com.citysos.api.incident.resources;

import com.citysos.api.police.domain.model.entity.Police;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedResource {
    private Integer id;
    private String comment;
    private Police police;
    private LocalDateTime date;
}
