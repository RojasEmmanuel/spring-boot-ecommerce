package com.emmadev.ecommerce.DTO;

import com.emmadev.ecommerce.domain.SexoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ClientUpdateDTO {
    @NotNull
    private final Long id;
    @NotBlank
    private final String nombre;
    @NotBlank
    private final String apellidos;
    @NotNull
    private final SexoEnum sexo;
}
