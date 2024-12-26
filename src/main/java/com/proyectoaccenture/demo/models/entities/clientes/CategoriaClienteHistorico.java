package com.proyectoaccenture.demo.models.entities.clientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name="categoriaClienteHistorico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaClienteHistorico {

    @Id
    @GeneratedValue
    private Long id;

    /* En caso de necesitarlo en un fututo podemos cambiar la dirección de la relación.
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; // Este es el lado propietario.
    */

    @Column
    private LocalDateTime fechaObtencion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaCliente categoria;

}
