package com.emmadev.ecommerce.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor

public class ProductoUpdateDTO {

    @NotNull
    private final Long id;
    @NotBlank
    @Length(min = 1, max = 50)
    private final String nombre;
    @Min(1)
    private final int existencia;
    @DecimalMin("10.0")
    private final BigDecimal precioVenta;
    @DecimalMin("10.0")
    private final BigDecimal precioCompra;
}
