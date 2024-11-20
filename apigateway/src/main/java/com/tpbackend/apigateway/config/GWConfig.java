package com.tpbackend.apigateway.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Aca configuramos el routeo de los dos microservicios.
public class GWConfig {
    // Paso como parámetro el modulo del microservicio pruebas, por ende la URI estará referenciada a este micro
    @Value("${microservicio-pruebas}")
    private String uriPruebas;

    @Value("${microservicio-vehiculos}")
    private String uriVehiculos;

    // Declaro un @Bean en el cual hacemos el configurador de rutas, permitiendo acceder a los respectivos microservicios
    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("pruebas-route", r -> r.path("/pruebas/¨**").uri(uriPruebas))
                .route("vehiculos-route", r -> r.path("/vehiculos/**").uri(uriVehiculos))
                .build();
    }
}
