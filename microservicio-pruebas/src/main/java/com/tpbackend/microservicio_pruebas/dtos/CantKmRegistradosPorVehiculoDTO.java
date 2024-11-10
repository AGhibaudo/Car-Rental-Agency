package com.tpbackend.microservicio_pruebas.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CantKmRegistradosPorVehiculoDTO {
    // Atributos de la prueba.
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    // Atributos del Vehiculo.
    private String patente;
    private Double kmRegistrados;
}