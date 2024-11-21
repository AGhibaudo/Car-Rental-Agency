package com.tpbackend.microservicio_pruebas.services.interfaces;

import com.tpbackend.microservicio_pruebas.entities.Empleado;
import com.tpbackend.microservicio_pruebas.entities.Prueba;

import java.util.List;
import java.time.LocalDateTime;

public interface EmpleadoService extends Service<Empleado, Long>{
    boolean estaDisponible(Empleado empleado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);
    List<Prueba> findByEmpleadoYFecha(Empleado empleado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);

}
