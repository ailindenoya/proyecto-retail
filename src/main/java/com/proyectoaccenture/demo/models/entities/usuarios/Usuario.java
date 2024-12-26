package com.proyectoaccenture.demo.models.entities.usuarios;

import com.proyectoaccenture.demo.models.entities.Persistente;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@Entity
@Table(name = "usuario")
@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Persistente {

    @Column(nullable = true, unique = true)
    private String nombreUsuario;

    @Column(nullable = true)
    private String contrasena;

    @Column(nullable = true, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_Non_Expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_Non_Expired")
    private boolean credentialsNonExpired;

    @Column(name = "account_Non_Locked")
    private boolean accountNonLocked;

}
