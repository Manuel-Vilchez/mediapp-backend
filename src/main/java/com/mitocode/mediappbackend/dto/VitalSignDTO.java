package com.mitocode.mediappbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignDTO {

    private Integer vitalSignId;

    @NotNull
    private Integer idPatient;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String temperature;

    @NotNull
    private String pulse;

    @NotNull
    private String respiratoryRhythm;

}
