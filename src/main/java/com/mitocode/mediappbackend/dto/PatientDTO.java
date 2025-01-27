package com.mitocode.mediappbackend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Integer idPatient;

    @NotNull
    //@NotEmpty
    //@NotBlank
    @Size(min = 3, max = 70, message = "{firstname.size}")
    private String firstName;

    @Size(min = 3, max = 70, message = "{lastName.size}")
    private String lastName;

    @NotNull
    private String dni;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phone;

    @NotNull
    @Email
    private String email;

    /*@Max(value = 999)
    @Min(value = 1)
    private int age;*/
}
