package com.schoolofliberation.academic.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudentDTO {
    
    @NotEmpty(message = "El nombre es requerido")
    private String name;

    @NotEmpty(message = "El apellido es requerido")
    private String lastname;

    @NotNull(message = "El dni es requerido")
    private Long dni;

    private String studentStatusMeaning;

    private Short studentCareer;
}
