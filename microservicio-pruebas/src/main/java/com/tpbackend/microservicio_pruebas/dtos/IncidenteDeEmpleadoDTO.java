package com.tpbackend.microservicio_pruebas.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteDeEmpleadoDTO {
    // Atributos del Empleado
    private Long legajo;
    private String empleadoApellido;
    private String empleadoNombre;
    private long telefonoContacto;
    private String patente; // Patente del Vehiculo.
    private String incidente; // Incidente ocurrido.
}
