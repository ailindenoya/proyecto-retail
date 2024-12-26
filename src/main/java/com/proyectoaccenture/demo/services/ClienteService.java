package com.proyectoaccenture.demo.services;

import com.proyectoaccenture.demo.exceptions.NotFoundException;
import com.proyectoaccenture.demo.externals.VentaOutputDTO;
import com.proyectoaccenture.demo.models.dtos.ClienteInputDTO;
import com.proyectoaccenture.demo.models.dtos.ClienteOutputDTO;
import com.proyectoaccenture.demo.models.dtos.DomicilioOutputDTO;
import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import com.proyectoaccenture.demo.models.entities.clientes.CategoriaClienteHistorico;
import com.proyectoaccenture.demo.models.entities.clientes.Cliente;
import com.proyectoaccenture.demo.models.entities.clientes.TipoCliente;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Domicilio;
import com.proyectoaccenture.demo.models.repositories.CategoriaClienteRepository;
import com.proyectoaccenture.demo.models.repositories.ClienteRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.proyectoaccenture.demo.models.repositories.DomicilioRepository;
import com.proyectoaccenture.demo.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final DomicilioRepository domicilioRepository;

    @Autowired
    private final CategoriaClienteRepository categoriaClienteRepository;

    @Autowired
    private DomicilioService domicilioService;



    public ClienteService(ClienteRepository clienteRepository, CategoriaClienteRepository categoriaClienteRepository, DomicilioService domicilioService, UsuarioRepository usuarioRepository, DomicilioRepository domicilioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaClienteRepository = categoriaClienteRepository;
        this.domicilioService = domicilioService;
        this.domicilioRepository = domicilioRepository;
    }


    public ClienteOutputDTO registrarCliente(ClienteInputDTO clienteInputDTO) {


        DomicilioOutputDTO domicilioOutput = this.domicilioService.
                cargarDomicilio(clienteInputDTO.getDireccion());

        Domicilio domicilio = this.domicilioRepository
            .findById(domicilioOutput.getId())
            .orElseThrow(() -> new UsernameNotFoundException("Domiclio no encontrado"));

        var usuarioInput = this.usuarioRepository
                        .findByNombreUsuario(clienteInputDTO
                        .getUsuario())
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var cliente = Cliente
                .builder()
                .usuario(usuarioInput)
                .nombre(clienteInputDTO.getNombre())
                .apellido(clienteInputDTO.getApellido())
                .dni(clienteInputDTO.getDni())
                .telefono(clienteInputDTO.getTelefono())
                .razonSocial(clienteInputDTO.getRazonSocial())
                .cuit(clienteInputDTO.getCuit())
                .tipo(TipoCliente.fromString(clienteInputDTO.getTipo()))
                .direccion(domicilio)
                .categoriaActual(categoriaClienteRepository.findByNivel("INICIAL"))
                .puntajeActual(0.0)
                .build();

        this.clienteRepository.save(cliente);

        return new ClienteOutputDTO(cliente);
    }

    public boolean bajaLogicaCliente(UUID cliente_id){
          Optional<Cliente> clienteOpt = clienteRepository.findById(cliente_id);
          if (clienteOpt.isEmpty()) {
              throw new UsernameNotFoundException("Cliente no encontrado");
          }
          Cliente cliente = clienteOpt.get();
          cliente.setActivo(false);
          this.clienteRepository.save(cliente);
          return true;
    }


    public List<ClienteOutputDTO> todasLosClientes() {
        return this.clienteRepository
                .findAll()
                .stream()
                .map(ClienteOutputDTO::new)
                .toList();
    }

    public ClienteOutputDTO getClienteByCuit(String cuit) {
        Optional<Cliente> clientePosible = this.clienteRepository.findByCuit(cuit);
        return new ClienteOutputDTO(clientePosible.orElseThrow(() -> {throw new NotFoundException("No se encontró un cliente con CUIT " + cuit);}));

    }

    public ClienteOutputDTO getClienteByDni(String dni) {
        Optional<Cliente> clientePosible = this.clienteRepository.findByDni(dni);
        return new ClienteOutputDTO(clientePosible.orElseThrow(() -> {throw new NotFoundException("No se encontró un cliente con DNI " + dni);}));
    }


    public void notificacionVenta(VentaOutputDTO venta) {
        //Se actualiza el puntaje del cliente
        this.acumularPuntos(venta.getClienteId(), venta.getPrecioTotalDeCompra());

        //Se chequea si se cambia de categoria o no, ademas se guarda el historico
        this.chequearCategoriaCliente(venta.getClienteId());

        System.out.println("llego: " + venta.toString());

    }


    public void chequearCategoriaCliente(UUID clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if(cliente.getPuntajeActual() > cliente.getCategoriaActual().getUmbralMinRequerido()){
            CategoriaClienteHistorico historico = new CategoriaClienteHistorico();
            historico.setCategoria(cliente.getCategoriaActual());
            historico.setFechaObtencion(LocalDateTime.now());
            cliente.getHistoricoCategorias().add(historico);
        }

        cliente.setCategoriaActual(cliente.getCategoriaActual().getSiguienteCategoria());

        clienteRepository.save(cliente);
    }

    public void acumularPuntos(UUID clienteId, BigDecimal precioTotalVenta) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setPuntajeActual(cliente.getPuntajeActual()
                + (precioTotalVenta.doubleValue() * cliente.getCategoriaActual().getFactorPuntosVenta()));

        clienteRepository.save(cliente);
    }

}