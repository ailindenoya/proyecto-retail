package com.proyectoaccenture.demo.models.dtos;

import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaClienteInputDTO {

    private String nivel;

    private Double umbralMinRequerido;

    private Long siguienteCategoria_id;

    private Double puntosParaSiguiente;

    private Double factorPuntosVenta;

}
