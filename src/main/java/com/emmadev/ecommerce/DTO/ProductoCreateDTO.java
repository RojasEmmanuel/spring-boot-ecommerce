package com.emmadev.ecommerce.DTO;

import com.emmadev.ecommerce.entity.Categoria;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductoCreateDTO {
    @NotBlank
    @Length(min = 1, max = 50)
    private final String nombre;
    @DecimalMin("10.0")
    private final BigDecimal precioVenta;
    @DecimalMin("10.0")
    private final BigDecimal precioCompra;
    @NotNull
    private final Long idCategoria;
    @Min(1)
    private final int existencia;
}
