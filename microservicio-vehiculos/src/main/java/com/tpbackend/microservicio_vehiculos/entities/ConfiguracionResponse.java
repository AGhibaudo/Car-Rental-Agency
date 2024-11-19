package com.tpbackend.microservicio_vehiculos.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfiguracionResponse {
    @JsonProperty("coordenadaAgencia")
    private CoordendaAgencia coordendaAgencia;

    @JsonProperty("radioAdmitidoEnKm")
    private Double radioAdmitidoEnKm;

    @JsonProperty("zonasRestringidas")
    private List<ZonaRestringida> zonasRestringidas;

}
