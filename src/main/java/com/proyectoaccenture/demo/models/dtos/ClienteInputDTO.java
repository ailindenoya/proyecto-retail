package com.proyectoaccenture.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInputDTO {

    private String usuario;

    private DomicilioInputDTO direccion;

    private String tipo;

    private String nombre;

    private String apellido;

    private String dni;

    private String razonSocial;

    private String cuit;

    private String telefono;

}
