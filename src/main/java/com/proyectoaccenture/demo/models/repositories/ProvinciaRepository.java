package com.proyectoaccenture.demo.models.repositories;

import com.proyectoaccenture.demo.models.entities.ubicaciones.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    Provincia findByNombre(String nombre);
}