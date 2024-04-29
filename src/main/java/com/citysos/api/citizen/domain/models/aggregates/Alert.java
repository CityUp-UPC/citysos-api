package com.citysos.api.citizen.domain.models.aggregates;

import com.citysos.api.citizen.domain.models.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "type_of_alert", length = 50)
    private String typeOfAlert;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    public Alert(LocalDateTime date, String typeOfAlert, EStatus status) {
        this.date = date;
        this.typeOfAlert = typeOfAlert;
        this.status = status;
    }
}
