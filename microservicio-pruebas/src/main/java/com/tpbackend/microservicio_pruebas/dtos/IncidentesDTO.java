package com.tpbackend.microservicio_pruebas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentesDTO {
    // Atributos de la prueba.
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    // Atributos del empleado
    private Long legajoEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    // Atributos del cliente
    private String nombreInteresado;
    private String apellidoInteresado;
    // Atributos de la notificación + vehiculo
    private String descripcionNotificacion;
    private String patente;
}