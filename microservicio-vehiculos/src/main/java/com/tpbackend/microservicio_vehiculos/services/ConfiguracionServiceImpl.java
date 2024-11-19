package com.tpbackend.microservicio_vehiculos.services;

import com.tpbackend.microservicio_vehiculos.entities.ConfiguracionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfiguracionServiceImpl {

    private final String apiUrl = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";

    @Autowired
    private RestTemplate restTemplate;

    public ConfiguracionResponse obtenerConfiguracion() {
        return restTemplate.getForObject(apiUrl, ConfiguracionResponse.class);
    }
}