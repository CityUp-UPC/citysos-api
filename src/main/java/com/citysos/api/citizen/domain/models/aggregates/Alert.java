package com.citysos.api.citizen.domain.models.aggregates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false, length = 10)
    private String date;

    @Column(name = "type_of_alert", nullable = false, length = 10)
    private String typeOfAlert;

    @Column(name = "status", nullable = false, length = 10)
    private String status;
}
