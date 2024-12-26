package com.proyectoaccenture.demo.models.repositories;

import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaClienteRepository extends JpaRepository<CategoriaCliente, Long> {
    CategoriaCliente findByNivel(String nivel);
}

