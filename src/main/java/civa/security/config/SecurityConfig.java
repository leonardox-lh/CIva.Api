package civa.security.config;

import civa.security.jwt.filter.TokenAuthenticationFilter;
import civa.security.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase que contiene la configuración de seguridad de la aplicación
 * @author Leonardo Lopez
 */
@Slf4j
@Configuration
@EnableWebSecurity //habilita la seguridad a nivel de aplicación
@EnableMethodSecurity //habilita la seguridad a nivel de método
public class SecurityConfig {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public SecurityConfig(
            TokenAuthenticationFilter tokenAuthenticationFilter,
            CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Bean que se encarga de manejar la configuración de la seguridad
     * @param http Objeto HttpSecurity
     * @return Objeto SecurityFilterChain
     * @throws Exception Excepción
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //se obtiene el authentication manager builder y se le indica que use el custom user details service y el password encoder
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        // Configuración de seguridad general
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // Asegúrate de que el CORS está habilitado
                .exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, authException) -> {
                    log.error("[!] UNAUTHORIZED ERROR -> {}", authException.getMessage());
                    response.sendError(401, authException.getMessage());
                }));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //filtro de autorización de rutas
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(
                            "/swagger-ui/**",
                            "/api/v1/auth/**",
                            "/v3/api-docs/**",
                            "/error",
                            "/favicon.ico"
                    )
                    .permitAll();

            authorize.anyRequest().authenticated();
        });

        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
