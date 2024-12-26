package com.proyectoaccenture.demo.services;

import com.proyectoaccenture.demo.models.dtos.ClienteOutputDTO;
import com.proyectoaccenture.demo.models.dtos.UsuarioInputDTO;
import com.proyectoaccenture.demo.models.dtos.UsuarioOutputDTO;
import com.proyectoaccenture.demo.models.entities.usuarios.Permiso;
import com.proyectoaccenture.demo.models.entities.usuarios.Rol;
import com.proyectoaccenture.demo.models.repositories.PermisoRepository;
import com.proyectoaccenture.demo.models.repositories.RolRepository;
import com.proyectoaccenture.demo.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository PermisoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(PasswordEncoder passwordEncoder,
                          UsuarioRepository usuarioRepository,
                          RolRepository rolRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }


    public UsuarioOutputDTO registrarUsuario(UsuarioInputDTO usuario) {

        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        Rol rol = rolRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuarioNuevo = Usuario.builder()
                .nombreUsuario(usuario.getNombreUsuario())
                .contrasena(passwordEncoder.encode(usuario.getContrasena()))
                .email(usuario.getEmail())
                .rol(rol)
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        usuarioRepository.save(usuarioNuevo);
        return new UsuarioOutputDTO(usuarioNuevo);
    }

    public boolean tienePermiso(String nombreUsuario, String accion) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return usuario.getRol().getPermisos().stream()
                .anyMatch(permiso -> permiso.getAccion().equals(accion));
    }

    public boolean modificarPermiso(String nombreUsuario, String accion, boolean tienePermiso) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (!usuarioOpt.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();
        Permiso permiso = PermisoRepository.findByAccion(accion);

        if (permiso == null) {
            throw new IllegalArgumentException("Acción no válida");
        }
        if (tienePermiso) {
            if (!usuario.getRol().getPermisos().contains(permiso)) {
                usuario.getRol().getPermisos().add(permiso);
            }
        } else {
            usuario.getRol().getPermisos().remove(permiso);
        }
        usuarioRepository.save(usuario);
        return true;
    }


    public boolean modificarUsuario(String nombreUsuario, String email, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (!usuarioOpt.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        usuario.setEmail(email);
        usuario.setContrasena(contrasena);

        usuarioRepository.save(usuario);
        return true;
    }


    public boolean bajaLogicaUsuario(String nombreUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
        return true;
    }

    public List<UsuarioOutputDTO> obtenerUsuarios(){
        return this.usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioOutputDTO::new)
                .toList();
    }


}