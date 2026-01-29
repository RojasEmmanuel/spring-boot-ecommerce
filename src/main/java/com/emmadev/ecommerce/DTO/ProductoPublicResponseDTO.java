package com.emmadev.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor

public class ProductoPublicResponseDTO {
    private final String nombre;
    private final BigDecimal precio;
    private final String categoria;
    private final int existencia;
}
