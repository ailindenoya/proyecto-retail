package com.proyectoaccenture.demo.controllers;

import com.proyectoaccenture.demo.externals.VentaOutputDTO;
import com.proyectoaccenture.demo.models.dtos.ClienteInputDTO;
import com.proyectoaccenture.demo.models.dtos.ClienteOutputDTO;
import com.proyectoaccenture.demo.models.entities.clientes.Cliente;
import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import com.proyectoaccenture.demo.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {

        this.clienteService = clienteService;
    }


    @GetMapping
    public ResponseEntity<List<ClienteOutputDTO>> todosLosClientes() {
        return new ResponseEntity<>(
                this.clienteService.todasLosClientes(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ClienteOutputDTO> registrarCliente(@RequestBody ClienteInputDTO cliente) {
        return new ResponseEntity<>(
                this.clienteService.registrarCliente(cliente),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/cuit/{cuit}")
    public ResponseEntity<ClienteOutputDTO> getClienteByCuit(@PathVariable String cuit) {
        return new ResponseEntity<>(
                this.clienteService.getClienteByCuit(cuit),
                HttpStatus.OK
        );
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ClienteOutputDTO> getClienteByDni(@PathVariable String dni) {
        return new ResponseEntity<>(
                this.clienteService.getClienteByDni(dni),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{cliente_id}")
    public ResponseEntity<Boolean> bajaLogicaCliente(@PathVariable UUID cliente_id) {
        return new ResponseEntity<>(
                this.clienteService.bajaLogicaCliente(cliente_id),
                HttpStatus.OK
        );
    }

    @PostMapping("/notificacion_venta")
    public ResponseEntity<String> notificarVenta(@RequestBody VentaOutputDTO venta) {
        this.clienteService.notificacionVenta(venta);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}