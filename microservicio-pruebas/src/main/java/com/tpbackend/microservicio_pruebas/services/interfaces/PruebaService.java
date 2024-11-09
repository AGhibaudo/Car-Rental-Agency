package com.tpbackend.microservicio_pruebas.services.interfaces;

import com.tpbackend.microservicio_pruebas.entities.Prueba;
import com.tpbackend.microservicio_pruebas.entities.Vehiculo;

import java.time.LocalDateTime;
import java.util.List;

public interface PruebaService extends Service<Prueba, Long>{
    List<Prueba> buscarPruebasEnCurso(LocalDateTime fechaActual);
    Prueba guardarPrueba(Prueba prueba);
    boolean elVehiculoEstaDisponible(Vehiculo vehiculo, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);
    List<Prueba> buscarVehiculoYFecha(Vehiculo vehiculo, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);
    String validarPreparacionPrueba(Prueba prueba);
    // DTO Incidentes {Falta implementar - Reportes}
}
