package com.proyectoaccenture.demo.config;


import com.proyectoaccenture.demo.services.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults()) // logueo sin token
                //.cors(Customizer.withDefaults())
                //   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // para CORS

                    // configuracion de endpoints publicos
                    http.requestMatchers(HttpMethod.GET, "/").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.GET, "/auth/get").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/ubicaciones/provincias").hasAuthority("CREATE");

                    http.requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll();
                    http.requestMatchers(HttpMethod.PATCH, "/api/usuarios/**").permitAll();

                    http.requestMatchers(HttpMethod.POST, "/api/clientes").permitAll();
                    http.requestMatchers(HttpMethod.PATCH, "/api/clientes/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/api/categorias").permitAll();

                    http.requestMatchers(HttpMethod.POST, "/api/clientes/notificacion_venta").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/clientes").permitAll();


                    // configuracion de endpoints privados
                    http.requestMatchers(HttpMethod.POST, "/auth/post").hasAuthority("CREATE");
                    http.requestMatchers("/login").permitAll();
                    // configuracion del resto de endpoints no especificados
                    http.anyRequest().permitAll(); // mas restrictivo y mas seguro

                    // .authenticated es para cuando ya estas logueado, no importa si tenes el rol
                })
                .formLogin(form ->
                        form.loginPage("/login").permitAll() // Aquí se establece tu página personalizada
                                .successHandler(authenticationSuccessHandler())
                )
                .oauth2Login(form ->
                        form.loginPage("/login").permitAll() // Aquí se establece tu página personalizada
                                .successHandler(authenticationSuccessHandler())).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws java.io.IOException {
                // Redirige a otro puerto
                response.sendRedirect("http://localhost:3000");
            }
        };
    }

}