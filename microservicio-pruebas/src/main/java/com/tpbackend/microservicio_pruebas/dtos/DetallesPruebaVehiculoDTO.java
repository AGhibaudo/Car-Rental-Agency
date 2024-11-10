package com.tpbackend.microservicio_pruebas.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallesPruebaVehiculoDTO {
    // Atributos del Vehiculo
    private Long vehiculoId;
    private String patente;
    private String descripcionModelo;
    private String nombreMarca;
    // Plazo de la prueba.
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    // Atributos del interesado.
    private String tipoDocumentoInteresado;
    private String documentoInteresado;
    private String apellidoInteresado;
    private String nombreInteresado;
    private long nroLicenciaInteresado;
    private LocalDateTime fechaVencimientoLicencia;
    // Atributos del Empleado.
    private Long legajoEmpleado;
    private String apellidoEmpleado;
    private String nombreEmpleado;
    // Comentario de la prueba.
    private String comentarios;
}