package com.proyectoaccenture.demo.models.repositories;


import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByEmail(String email);

    boolean existsByNombreUsuario(String nombreUsuario);
}