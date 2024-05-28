package com.citysos.api.police.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "news")
@Entity
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    //private latitude
    private String district;

    private LocalDateTime date;

    @JoinColumn(name = "police_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Police police;

//    @OneToMany(mappedBy = "new", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Image> images;
//
//    @OneToMany(mappedBy = "new", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> comments;

    @PrePersist
    public void prePersist(){
        date = LocalDateTime.now();
    }




}
