package com.proyectoaccenture.demo.controllers;


import com.proyectoaccenture.demo.models.dtos.CategoriaClienteInputDTO;
import com.proyectoaccenture.demo.models.dtos.CategoriaClienteOutputDTO;
import com.proyectoaccenture.demo.models.dtos.ClienteInputDTO;
import com.proyectoaccenture.demo.models.dtos.ClienteOutputDTO;
import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import com.proyectoaccenture.demo.services.CategoriaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin
public class CategoriaClienteController {

    @Autowired
    private CategoriaClienteService categoriaClienteService;

    public CategoriaClienteController(CategoriaClienteService categoriaClienteService) {
        this.categoriaClienteService = categoriaClienteService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaClienteOutputDTO>> obtenerTodasLasCategorias() {
        return new ResponseEntity<>(
                this.categoriaClienteService.obtenerTodasLasCategorias(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CategoriaClienteOutputDTO> registrarCliente(@RequestBody CategoriaClienteInputDTO categoriaInput) {
        return new ResponseEntity<>(
                this.categoriaClienteService.crearCategoria(categoriaInput),
                HttpStatus.CREATED
        );
    }
}