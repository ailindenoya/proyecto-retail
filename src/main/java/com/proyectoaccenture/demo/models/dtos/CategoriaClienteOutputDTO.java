package com.proyectoaccenture.demo.models.dtos;

import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import lombok.Data;

@Data
public class CategoriaClienteOutputDTO {

    private String nivel;

    private Double umbralMinRequerido;

    private Long siguienteCategoria_id;

    private Double puntosParaSiguiente;

    private Double factorPuntosVenta;

    public CategoriaClienteOutputDTO(CategoriaCliente categoriaCliente){
        this.nivel = categoriaCliente.getNivel();
        this.umbralMinRequerido = categoriaCliente.getUmbralMinRequerido();
        this.siguienteCategoria_id = (categoriaCliente.getSiguienteCategoria() != null)
                ? categoriaCliente.getSiguienteCategoria().getId()
                : null;
        this.puntosParaSiguiente = categoriaCliente.getPuntosParaSiguiente();
        this.factorPuntosVenta = categoriaCliente.getFactorPuntosVenta();
    }
}
