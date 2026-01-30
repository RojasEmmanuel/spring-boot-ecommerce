package com.emmadev.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_itemCompra")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private BigDecimal subtotal;
    private String operacion;
    private LocalDateTime ultimaModificacion;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @PrePersist
    public void prepersist(){
        this.operacion = "Nuevo";
        this.ultimaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preupdate(){
        this.operacion = "Item actualizado";
        this.ultimaModificacion = LocalDateTime.now();
    }
}
