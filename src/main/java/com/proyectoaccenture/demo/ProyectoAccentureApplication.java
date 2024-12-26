package com.proyectoaccenture.demo;

import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import com.proyectoaccenture.demo.models.entities.ubicaciones.GeorefService;
import com.proyectoaccenture.demo.models.entities.ubicaciones.ProvinciasResponse;
import com.proyectoaccenture.demo.models.entities.usuarios.Permiso;
import com.proyectoaccenture.demo.models.entities.usuarios.Rol;
import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import com.proyectoaccenture.demo.models.repositories.CategoriaClienteRepository;
import com.proyectoaccenture.demo.models.repositories.UsuarioRepository;
import com.proyectoaccenture.demo.models.repositories.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@SpringBootApplication
//@EnableFeignClients
//@EnableDiscoveryClient
public class ProyectoAccentureApplication {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProyectoAccentureApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(GeorefService georefService, UsuarioRepository usuarioRepository, RolRepository rolRepository, CategoriaClienteRepository categoriaClienteRepository) {
		return args -> {
			georefService.cargarProvinciasYLocalidades();

			Permiso createPermiso = Permiso.builder()
			.accion("CREATE")
			.build();
			Permiso readPermiso = Permiso.builder()
					.accion("READ")
					.build();
			Permiso updatePermiso = Permiso.builder()
					.accion("UPDATE")
					.build();
			Permiso deletePermiso = Permiso.builder()
					.accion("DELETE")
					.build();
			Permiso refactorPermiso = Permiso.builder()
					.accion("REFACTOR")
					.build();

			Rol rolADMIN  = Rol.builder()
					.rol("ADMIN")
					.permisos(Set.of(createPermiso, readPermiso, updatePermiso, deletePermiso))
					.build();
			Rol rolUSER  = Rol.builder()
					.rol("USER")
					.permisos(Set.of(createPermiso, readPermiso))
					.build();
			Rol rolINVITED  = Rol.builder()
					.rol("INVITED")
					.permisos(Set.of(readPermiso))
					.build();
			Rol rolDEV  = Rol.builder()
					.rol("DEV")
					.permisos(Set.of(createPermiso, readPermiso, updatePermiso, deletePermiso, refactorPermiso))
					.build();

			Usuario user1 = Usuario.builder()
					.nombreUsuario("admin")
					.contrasena("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.rol(rolADMIN)
					.email("example1@example.com")
					.build();
			Usuario user2 = Usuario.builder()
					.nombreUsuario("user")
					.contrasena("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.rol(rolUSER)
					.email("example2@example.com")
					.build();
			Usuario user3 = Usuario.builder()
					.nombreUsuario("inv")
					.contrasena("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.rol(rolINVITED)
					.email("exampl3e@example.com")
					.build();
			Usuario user4 = Usuario.builder()
					.nombreUsuario("dev")
					.contrasena("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.rol(rolDEV)
					.email("example4@example.com")
					.build();





			rolRepository.saveAll(List.of(rolADMIN, rolUSER, rolINVITED, rolDEV));
			usuarioRepository.saveAll(List.of(user1, user2, user3, user4));
		};
	}

}
