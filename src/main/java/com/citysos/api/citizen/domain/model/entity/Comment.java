package com.citysos.api.citizen.domain.model.entity;

import com.citysos.api.police.domain.model.entity.New;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @JoinColumn(name = "citizen_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Citizen citizen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "new_id", nullable = false)
    private New news;
}
