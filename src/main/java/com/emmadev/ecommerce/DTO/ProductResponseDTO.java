package com.emmadev.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductResponseDTO {
    private final String nombre;
    private final BigDecimal precioCompra;
    private final BigDecimal precioVenta;
    private final String categoria;
    private final int existencia;

    private final String operacion;
    private final LocalDateTime ultimaModificacion;
}
