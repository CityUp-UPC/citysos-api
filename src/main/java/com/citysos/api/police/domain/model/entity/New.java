package com.citysos.api.police.domain.model.entity;

import com.citysos.api.citizen.domain.model.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Table(name = "news")
@Entity
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String district;

    private LocalDateTime date;

    @JoinColumn(name = "police_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Police police;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;

    @PrePersist
    public void prePersist(){
        date = LocalDateTime.now();
    }
}
