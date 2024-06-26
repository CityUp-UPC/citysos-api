package com.citysos.api.incident.domain.entity;

import com.citysos.api.police.domain.model.entity.Police;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "feeds")
@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "police_id", nullable = false)
    private Police givenPolice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "incident_id", nullable = false)
    private Incident givenIncident;

    private LocalDateTime date;

    @PrePersist
    void prePersist(){
        date = LocalDateTime.now();
    }
}
