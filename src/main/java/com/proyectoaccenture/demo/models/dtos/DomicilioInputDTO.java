package com.proyectoaccenture.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioInputDTO {

    private Integer numeroCalle;

    private String calle;

    private String codigoPostal;

    private String piso;

    private String departamento;

    private Long localidadId;

}
