package com.emmadev.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String operacion;
    private LocalDateTime ultimaModificacion;

    @OneToMany(mappedBy = "categoria")
    @JsonManagedReference
    private List<Producto> productos;

    @PrePersist
    public void prepersist(){
        this.operacion = "Nuevo";
        this.ultimaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preupdate(){
        this.operacion = "Actualizado";
        this.ultimaModificacion = LocalDateTime.now();
    }
}
