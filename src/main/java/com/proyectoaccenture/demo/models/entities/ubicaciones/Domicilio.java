package com.proyectoaccenture.demo.models.entities.ubicaciones;

import com.proyectoaccenture.demo.models.entities.Persistente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="domicilio")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Domicilio extends Persistente {

    //ATRIBUTOS
    @Column
    private Integer numeroCalle;

    @Column
    private String calle;

    @Column
    private String codigoPostal;

    @Column
    private String piso;

    @Column
    private String departamento;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = true)
    private Localidad localidad;
}