package com.proyectoaccenture.demo.models.entities.usuarios;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permiso")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permiso {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String accion;
}
