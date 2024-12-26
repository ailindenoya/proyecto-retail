package com.proyectoaccenture.demo.models.dtos;

import com.proyectoaccenture.demo.models.entities.ubicaciones.Domicilio;
import lombok.Data;

import java.util.UUID;

@Data
public class DomicilioOutputDTO {

    private UUID id;

    private Integer numeroCalle;

    private String calle;

    private String codigoPostal;

    private String piso;

    private String departamento;

    private Long localidadId;

    public DomicilioOutputDTO (Domicilio domicilio){
        this.id = domicilio.getId();
        this.numeroCalle = domicilio.getNumeroCalle();
        this.calle = domicilio.getCalle();
        this.codigoPostal = domicilio.getCodigoPostal();
        this.piso = domicilio.getPiso();
        this.departamento = domicilio.getDepartamento();
        this.localidadId = domicilio.getLocalidad().getId();
    }
}
