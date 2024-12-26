package com.proyectoaccenture.demo.models.repositories;

import com.proyectoaccenture.demo.models.entities.ubicaciones.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {

    Optional<Domicilio> findById(UUID id);
}
