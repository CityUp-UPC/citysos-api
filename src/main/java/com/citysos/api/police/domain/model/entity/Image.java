package com.citysos.api.police.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "images")
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    @JsonIgnore
    @JoinColumn(name = "new_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private New news;
}
