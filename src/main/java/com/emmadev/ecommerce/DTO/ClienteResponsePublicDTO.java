package com.emmadev.ecommerce.DTO;

import com.emmadev.ecommerce.domain.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ClienteResponsePublicDTO {
    private final String nombreCompleto;
    private final SexoEnum sexo;
    private final LocalDate fechaNacimiento;
    private final int edad;
}
