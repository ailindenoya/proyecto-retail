package com.proyectoaccenture.demo.models.repositories;

import com.proyectoaccenture.demo.models.entities.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    //Metodos creados por defecto por JPA, save, delete, equals, toString, find, ... .
    Optional<Cliente> findById(UUID id);

    Optional<Cliente> findByCuit(String cuit);

    Optional<Cliente> findByDni(String dni);
}
