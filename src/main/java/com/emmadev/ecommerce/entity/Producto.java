package com.emmadev.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @Column(name = "precio_compra")
    private BigDecimal precioCompra;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    private int existencia;
    private String operacion;

    @Column(name = "ultima_modificacion")
    private LocalDateTime ultimaModificacion;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<ItemCompra> carrito;

    @PrePersist
    public void crear(){
        this.operacion = "Nuevo";
        this.ultimaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.operacion = "Actualizado";
        this.ultimaModificacion = LocalDateTime.now();
    }
}
