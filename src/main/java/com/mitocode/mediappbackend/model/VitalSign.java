package com.mitocode.mediappbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vitalSignId;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_SIGN_PATIENT"))
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, length = 4)
    private String temperature;

    @Column(nullable = false, length = 4)
    private String pulse;

    @Column(nullable = false)
    private String respiratoryRhythm;
}
