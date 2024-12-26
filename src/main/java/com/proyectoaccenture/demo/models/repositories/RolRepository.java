package com.proyectoaccenture.demo.models.repositories;

import com.proyectoaccenture.demo.models.entities.usuarios.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
