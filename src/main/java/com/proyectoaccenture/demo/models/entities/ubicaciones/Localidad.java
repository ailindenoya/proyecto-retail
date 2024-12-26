package com.proyectoaccenture.demo.models.entities.ubicaciones;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="localidad")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Localidad {

    //ATRIBUTOS
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id", nullable = false)
    private Provincia provincia;

}
