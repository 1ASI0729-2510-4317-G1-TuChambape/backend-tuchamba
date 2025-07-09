package com.acme.jobconnect.platform.security.config;

import com.acme.jobconnect.platform.security.jwt.AuthorizationFilter;
import com.acme.jobconnect.platform.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthorizationFilter authorizationFilter;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthorizationFilter authorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivar CSRF, ya que usamos JWT y no sesiones
                .csrf(AbstractHttpConfigurer::disable)
                // Configurar las reglas de autorización para cada ruta
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/api/v1/authentication/**", // Permitir todo en /authentication
                                "/v3/api-docs/**",         // Permitir acceso a la documentación de OpenAPI
                                "/swagger-ui/**",           // Permitir acceso a la UI de Swagger
                                "/swagger-ui.html"
                        ).permitAll() // Estas rutas no requieren autenticación
                        .anyRequest().authenticated() // Cualquier otra ruta debe ser autenticada
                )
                // Configurar la gestión de sesiones como SIN ESTADO (stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Registrar nuestro proveedor de autenticación personalizado
                .authenticationProvider(authenticationProvider())
                // Añadir nuestro filtro de autorización JWT antes del filtro de usuario/contraseña
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}