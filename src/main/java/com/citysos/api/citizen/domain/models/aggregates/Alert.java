package com.citysos.api.citizen.domain.models.aggregates;

import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.citizen.domain.models.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id_alert")
    private Long id;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "type_of_alert", length = 50)
    private String typeOfAlert;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public Alert(UserEntity user, Instant date, String typeOfAlert, EStatus status) {
        this.user = user;
        this.date = date;
        this.typeOfAlert = typeOfAlert;
        this.status = status;
    }
}
