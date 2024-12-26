package com.proyectoaccenture.demo.models.dtos;

import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import com.proyectoaccenture.demo.models.entities.clientes.CategoriaClienteHistorico;
import com.proyectoaccenture.demo.models.entities.clientes.TipoCliente;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Domicilio;
import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import com.proyectoaccenture.demo.models.entities.clientes.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ClienteOutputDTO {

    private UUID clienteId;

    private UsuarioOutputDTO usuario;

    private DomicilioOutputDTO direccion;

    private Double puntajeActual;

    private String tipoCliente;

    private String nombreApellido;

    private String dni;

    private String razonSocial;

    private String cuit;

    private String telefono;

    private String categoriaActual;

    public ClienteOutputDTO(Cliente cliente){
        this.clienteId = cliente.getId();
        this.tipoCliente = cliente.getTipo().toString();
        this.puntajeActual = cliente.getPuntajeActual();
        this.nombreApellido = cliente.getNombre() + " " + cliente.getApellido();
        this.dni = cliente.getDni();
        this.razonSocial = cliente.getRazonSocial();
        this.cuit = cliente.getCuit();
        this.telefono = cliente.getTelefono();
        this.categoriaActual = cliente.getCategoriaActual().getNivel();
        this.direccion = new DomicilioOutputDTO(cliente.getDireccion());
        this.usuario = new UsuarioOutputDTO(cliente.getUsuario());
    }
}
