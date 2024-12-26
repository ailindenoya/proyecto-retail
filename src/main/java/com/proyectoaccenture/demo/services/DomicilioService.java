package com.proyectoaccenture.demo.services;

import com.proyectoaccenture.demo.exceptions.NotFoundException;
import com.proyectoaccenture.demo.models.dtos.DomicilioInputDTO;
import com.proyectoaccenture.demo.models.dtos.DomicilioOutputDTO;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Domicilio;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Localidad;
import com.proyectoaccenture.demo.models.repositories.DomicilioRepository;
import com.proyectoaccenture.demo.models.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private LocalidadRepository localidadRepository;

    public DomicilioService(DomicilioRepository domicilioRepository, LocalidadRepository localidadRepository){
        this.domicilioRepository = domicilioRepository;
        this.localidadRepository = localidadRepository;
    }


    public DomicilioOutputDTO cargarDomicilio(DomicilioInputDTO domicilioInputDTO){

        Localidad localidad = localidadRepository
                        .findById(domicilioInputDTO
                        .getLocalidadId())
                        .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

       var domicilio = Domicilio
                .builder()
                .calle(domicilioInputDTO.getCalle())
                .numeroCalle(domicilioInputDTO.getNumeroCalle())
                .departamento(domicilioInputDTO.getDepartamento())
                .piso(domicilioInputDTO.getPiso())
                .codigoPostal(domicilioInputDTO.getCodigoPostal())
                .localidad(localidad)
                .build();

        this.domicilioRepository.save(domicilio);

        return new DomicilioOutputDTO(domicilio);
    }



}