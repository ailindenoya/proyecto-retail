package com.proyectoaccenture.demo.controllers;

import com.proyectoaccenture.demo.models.entities.ubicaciones.GeorefService;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Localidad;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Provincia;
import com.proyectoaccenture.demo.services.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin
public class UbicacionController {

    @Autowired
    private GeorefService georefService;


    @GetMapping("/provincias")
    public ResponseEntity<List<Provincia>> obtenerProvincias(){
        return new ResponseEntity<>(
                this.georefService.obtenerProvincias(),
                HttpStatus.OK
        );
    }

    @GetMapping("/localidades")
    public ResponseEntity<List<Localidad>> obtenerLocalidad(){
        return new ResponseEntity<>(
                this.georefService.obtenerLocalidades(),
                HttpStatus.OK
        );
    }

}