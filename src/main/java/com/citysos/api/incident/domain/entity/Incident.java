package com.citysos.api.incident.domain.entity;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.police.domain.model.entity.Police;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "incidents")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String description;

    private LocalDateTime date;

    //private Location location
    private String latitude;

    private String longitude;

    private String address;

    private String district;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "incident_police",
            joinColumns = @JoinColumn(name = "incident_id"),
            inverseJoinColumns = @JoinColumn(name = "police_id")
    )
    private List<Police> police;

    @PrePersist
    public void prePersist(){
        date = LocalDateTime.now();
        if(status==null){
            status = Status.PENDIENT;
        }
    }
}
