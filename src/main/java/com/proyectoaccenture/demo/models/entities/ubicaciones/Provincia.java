package com.proyectoaccenture.demo.models.entities.ubicaciones;

import com.proyectoaccenture.demo.models.entities.Persistente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="provincia")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Provincia {

    //ATRIBUTOS
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

}
