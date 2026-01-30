package com.emmadev.ecommerce.DTO;

import com.emmadev.ecommerce.domain.SexoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ClienteCreateDTO {
    @NotBlank
    private final String nombre;
    @Length(min=5)
    private final String apellidos;
    @NotNull
    private final SexoEnum sexo;
    @NotNull
    private final LocalDate fechaNacimiento;
}
