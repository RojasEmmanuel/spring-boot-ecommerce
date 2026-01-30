package com.emmadev.ecommerce.DTO;

import com.emmadev.ecommerce.domain.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class ClienteReponseDTO {
    private final String nombreCompleto;
    private final SexoEnum sexo;
    private final LocalDate fechaNacimiento;
    private final int edad;
    private final String operacion;
    private final LocalDateTime ultimaModificacion;
    private final String antiguedad;
}
