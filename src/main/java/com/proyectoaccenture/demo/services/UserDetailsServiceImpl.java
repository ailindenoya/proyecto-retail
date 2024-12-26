package com.proyectoaccenture.demo.services;

import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import com.proyectoaccenture.demo.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("el usuario " + username + "no existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        authorityList
                .add( new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getRol()));

        usuario.getRol()
                .getPermisos()
                .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getAccion()))); // no necesitan el ROLE_

        return new User(usuario.getNombreUsuario(),
                usuario.getContrasena(),
                usuario.isEnabled(),
                usuario.isAccountNonExpired(),
                usuario.isCredentialsNonExpired(),
                usuario.isAccountNonLocked(),
                authorityList);
    }
}
