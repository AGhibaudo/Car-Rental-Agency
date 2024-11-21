package com.tpbackend.apigateway.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class GWSecurity {

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("https://labsys.frc.utn.edu.ar/aim/realms/backend-tps/protocol/openid-connect/certs").build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints a los que pueden acceder los usuarios con el rol Empleado
                        .requestMatchers("/pruebas/status").hasAuthority("Empleado")
                        .requestMatchers("/pruebas/en_curso").hasAuthority("Empleado")
                        .requestMatchers("/pruebas/{id}/finalizar").hasAuthority("Empleado")
                        .requestMatchers("/vehiculos/status").hasAuthority("Empleado")
                        .requestMatchers(HttpMethod.POST, "/pruebas").hasAuthority("Empleado")
                        .requestMatchers(HttpMethod.POST, "/pruebas/notificar").hasAuthority("Empleado")

                        // Endpoints a los que pueden acceder los usuarios con el rol Vehiculo
                        .requestMatchers(HttpMethod.POST, "/vehiculos/evaluar").hasAuthority("Vehiculo")

                        // Endpoints a los que pueden acceder los usuarios con el rol Admin
                        .requestMatchers("/pruebas/reporte/incidentes").hasAuthority("Admin")
                        .requestMatchers("/pruebas/reporte/incidentes/**").hasAuthority("Admin")
                        .requestMatchers("/pruebas/reporte/cant_km_vehiculo/**").hasAuthority("Admin")
                        .requestMatchers("/pruebas/reporte/detalle_vehiculo/**").hasAuthority("Admin")

                        // Endpoints a los que puede cualquier usuario autenticado.
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                ); // Se encarga de configurar la validación del JWT para aquellos endpoints protegidos
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Configura el prefijo para los roles (según cómo se define en tu JWT)
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities"); // El nombre del campo en el JWT con los roles

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
