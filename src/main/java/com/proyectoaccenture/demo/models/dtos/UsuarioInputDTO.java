package com.proyectoaccenture.demo.models.dtos;

import com.proyectoaccenture.demo.models.entities.usuarios.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
public class UsuarioInputDTO {

    private String nombreUsuario;

    private String contrasena;

    private String email;

    private long rolID;

}
