package com.emmadev.ecommerce.entity;

import com.emmadev.ecommerce.domain.SexoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    private SexoEnum sexo;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private int edad;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private String operacion;

    @Column(name = "ultima_modificacion")
    private LocalDateTime ultimaModificacion;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Compra> compras;

    @PrePersist
    public void prepersist(){
        this.operacion = "Nuevo";
    }

    @PreUpdate
    public void preupdate(){
        this.operacion = "Actualizado";
        this.ultimaModificacion = LocalDateTime.now();
    }
}
