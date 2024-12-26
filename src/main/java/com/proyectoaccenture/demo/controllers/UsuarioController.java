package com.proyectoaccenture.demo.controllers;

import com.proyectoaccenture.demo.models.dtos.UsuarioInputDTO;
import com.proyectoaccenture.demo.models.dtos.UsuarioOutputDTO;
import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import com.proyectoaccenture.demo.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioOutputDTO> registrarUsuario(@RequestBody UsuarioInputDTO usuarioInputDTO) {
        return new ResponseEntity<>(
                this.usuarioService.registrarUsuario(usuarioInputDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("/{nombreUsuario}/permiso")
    public ResponseEntity<Boolean> tienePermiso(@PathVariable String nombreUsuario, @RequestParam String accion) {
      return new ResponseEntity<>(
              this.usuarioService.tienePermiso(nombreUsuario, accion),
              HttpStatus.OK
      );
    }

    @GetMapping
    public ResponseEntity<List<UsuarioOutputDTO>> obtenerUsuarios() {
        return new ResponseEntity<>(
                this.usuarioService.obtenerUsuarios(),
                HttpStatus.OK
        );
    }



    @PatchMapping("/{nombreUsuario}/permiso")
    public ResponseEntity<Boolean> modificarPermiso(@PathVariable String nombreUsuario, @RequestParam String accion, @RequestParam boolean tienePermiso) {
        return new ResponseEntity<>(
                this.usuarioService.modificarPermiso(nombreUsuario, accion, tienePermiso),
                HttpStatus.OK
        );
    }


    @PatchMapping("/{nombreUsuario}")
    public ResponseEntity<Boolean> modificarUsuario(@PathVariable String nombreUsuario, @RequestParam String email, @RequestParam String contrasena) {
        return new ResponseEntity<>(
                this.usuarioService.modificarUsuario(nombreUsuario, email, contrasena),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{nombreUsuario}")
    public ResponseEntity<Boolean> bajaLogicaUsuario(@PathVariable String nombreUsuario) {
        return new ResponseEntity<>(
                this.usuarioService.bajaLogicaUsuario(nombreUsuario),
                HttpStatus.OK
        );
    }
}