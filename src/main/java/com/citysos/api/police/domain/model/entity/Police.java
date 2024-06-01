package com.citysos.api.police.domain.model.entity;

import com.citysos.api.auth.domain.model.entity.User;
import com.citysos.api.incident.domain.entity.Incident;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Table(name = "polices")
@Entity
public class Police {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String assignDistrict;
    @NotNull
    private Boolean inService;
    @NotNull
    private String policeRank;
    @NotNull
    private String policeIdentifier;
    @NotNull
    private String entityPolice;
    private String latitude;
    private String longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToMany(mappedBy = "police")
    private List<Incident> incidents;
}
