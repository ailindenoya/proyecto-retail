package com.proyectoaccenture.demo.models.repositories;

import com.proyectoaccenture.demo.models.entities.usuarios.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    Permiso findByAccion(String accion);
}