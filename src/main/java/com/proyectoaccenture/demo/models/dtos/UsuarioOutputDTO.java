package com.proyectoaccenture.demo.models.dtos;

import com.proyectoaccenture.demo.models.entities.usuarios.Rol;
import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import com.proyectoaccenture.demo.models.repositories.RolRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class UsuarioOutputDTO {

    private String nombreUsuario;

// sacamos contrasena porque es un dato sensible que NO queremos devolver

    private String email;

    private Long rolId;

    public UsuarioOutputDTO(Usuario usuario) {
        this.nombreUsuario = usuario.getNombreUsuario();
        this.email = usuario.getEmail();
        this.rolId = usuario.getRol().getId();
    }

}
