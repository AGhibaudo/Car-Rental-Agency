package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Prueba;
import com.tpbackend.microservicio_pruebas.entities.Vehiculo;
import com.tpbackend.microservicio_pruebas.dtos.IncidentesDTO;
import com.tpbackend.microservicio_pruebas.dtos.IncidenteDeEmpleadoDTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PruebaRepository extends CrudRepository<Prueba, Long> {
    // La siguiente consulta busca si el vehículo está disponible.
    @Query("SELECT p " +
            "FROM Prueba p " +
            "WHERE p.vehiculo = :vehiculo AND " +
            "((p.fechaHoraInicio <= :fechaHoraFin " +
            "AND p.fechaHoraFin >= :fechaHoraInicio))")
    List<Prueba> buscarVehiculoYFecha(@Param("vehiculo") Vehiculo vehiculo,
                                      @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                      @Param("fechaHoraFin") LocalDateTime fechaHoraFin);

    // Está consulta verífica si al prueba está en curso.
    @Query("SELECT p " +
            "FROM Prueba p " +
            "WHERE p.fechaHoraInicio <= :fechaActual AND " +
            "p.fechaHoraFin >= :fechaActual")
    List<Prueba> buscarPruebasEnCurso(@Param("fechaActual") LocalDateTime fechaActual);


    // Reportes por parte de los Incidentes - Punto 1 -> F del enunciado

    // i. Incidentes (Pruebas donde se excedieron los límites establecidos).
    @Query("SELECT new com.tpbackend.microservicio_pruebas.dtos.IncidentesDTO(" +
            "p.id, p.fechaHoraInicio, p.fechaHoraFin, e.legajo, e.nombre, e.apellido, " +
            "i.nombre, i.apellido, n.descripcion, v.patente) " +
            "FROM Prueba p JOIN Empleado e ON p.empleado.legajo = e.legajo " +
            "JOIN Notificacion n ON n.legajo = e.legajo " +
            "JOIN Interesado i ON p.interesado.id = i.id " +
            "JOIN Vehiculo v ON p.vehiculo.id = v.id " +
            "WHERE n.descripcion IS NOT NULL")
    List<IncidentesDTO> buscarIncidentes();

    // ii. Detalle de incidentes para un empleado
    @Query("SELECT com.tpbackend.microservicio_pruebas.dtos.IncidenteDeEmpleadoDTO(" +
            "e.legajo, " +
            "e.apellido, " +
            "e.nombre, " +
            "e.telefono, " +
            "v.patente, " +
            "n.descripcion) " +
            "FROM Prueba p " +
            "JOIN Empleado e ON p.empleado.id = e.legajo " +
            "JOIN Notificacion n ON n.legajo = e.legajo " +
            "JOIN Vehiculo v ON p.vehiculo.id = v.id " +
            "WHERE n.descripcion IS NOT NULL " +
            "AND (n.descripcion LIKE '%radio%' OR n.descripcion LIKE '%restringida%') " +
            "AND e.legajo = :legajo " +
            "GROUP BY n.descripcion, e.legajo")
    List<IncidenteDeEmpleadoDTO> buscarIncidentesParaUnEmpleado(@Param("legajo") Long legajo);
}