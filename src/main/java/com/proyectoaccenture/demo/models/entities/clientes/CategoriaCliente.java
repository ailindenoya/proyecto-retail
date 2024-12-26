package com.proyectoaccenture.demo.models.entities.clientes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="categoriaCliente")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoriaCliente {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nivel;

    @Column
    private Double umbralMinRequerido;

    @OneToOne
    @JoinColumn(name = "siguiente_categoria_id", nullable = true)
    private CategoriaCliente siguienteCategoria;

    @Column
    private Double puntosParaSiguiente;

    @Column
    private Double factorPuntosVenta;

}
