package com.proyectoaccenture.demo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class Persistente {

    //ENTIDAD COMUN A TODAS LAS CLASES QUE SE PERSISTAN
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //BAJA LOGICA
    @Column
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = true)
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        if (this.fechaActualizacion == null) {
            this.fechaActualizacion = this.fechaCreacion;
        } //todo
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
