package com.emmadev.ecommerce.DTO;

import com.emmadev.ecommerce.domain.EstatusVenta;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CompraReponseDTO {

    private final Long id;
    private final LocalDateTime fechaCreacion;
    private final EstatusVenta estatus;
    private final String cliente;
    private final BigDecimal total;
}
