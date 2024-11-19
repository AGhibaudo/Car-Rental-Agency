package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Prueba;
import com.tpbackend.microservicio_pruebas.entities.Vehiculo;
import com.tpbackend.microservicio_pruebas.dtos.IncidentesDTO;
import com.tpbackend.microservicio_pruebas.dtos.IncidenteDeEmpleadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    // La siguiente consulta busca si el vehículo está disponible.
    @Query("SELECT P " +
            "FROM Prueba P " +
            "WHERE P.vehiculo = :vehiculo AND " +
            "((P.fechaHoraInicio <= :fechaHoraFin " +
            "AND P.fechaHoraFin >= :fechaHoraInicio))")
    List<Prueba> buscarVehiculoYFecha(@Param("vehiculo") Vehiculo vehiculo,
                                      @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                      @Param("fechaHoraFin") LocalDateTime fechaHoraFin);

    // Está consulta verífica si al prueba está en curso.
    @Query("SELECT P " +
            "FROM Prueba P " +
            "WHERE P.fechaHoraInicio <= :fechaActual AND " +
            "P.fechaHoraFin >= :fechaActual")
    List<Prueba> buscarPruebasEnCurso(@Param("fechaActual") LocalDateTime fechaActual);


    // Reportes por parte de los Incidentes - Punto 1 -> F del enunciado

    // i. Incidentes (Pruebas donde se excedieron los límites establecidos).
    @Query("SELECT new com.tpbackend.microservicio_pruebas.dtos.IncidentesDTO(" +
            "P.id, P.fechaHoraInicio, P.fechaHoraFin, E.legajo, E.nombre, E.apellido, " +
            "I.nombre, I.apellido, N.descripcion, V.patente) " +
            "FROM Prueba P JOIN Empleado E ON P.empleado.legajo = E.legajo " +
            "JOIN Notificacion N ON N.legajo = E.legajo " +
            "JOIN Interesado I ON P.interesado.id = I.id " +
            "JOIN Vehiculo V ON P.vehiculo.id = V.id " +
            "WHERE N.descripcion IS NOT NULL")
    List<IncidentesDTO> buscarIncidentes();

    // ii. Detalle de incidentes para un empleado
    @Query("SELECT new com.tpbackend.microservicio_pruebas.dtos.IncidenteDeEmpleadoDTO(" +
            "E.legajo, E.apellido, E.nombre, E.telefonoContacto, V.patente, N.descripcion) " +
            "FROM Prueba P JOIN Empleado E ON P.empleado.id = E.legajo " +
            "JOIN Notificacion N ON N.legajo = E.legajo " +
            "JOIN Vehiculo V ON P.vehiculo.id = V.id" +
            "WHERE N.descripcion IS NOT NULL " +
            "AND (N.descripcion LIKE '%radio%' OR N.descripcion LIKE '%restringida%') " +
            "AND E.legajo = :legajo " +
            "GROUP BY N.descripcion, E.legajo")
    List<IncidenteDeEmpleadoDTO> buscarIncidentesParaUnEmpleado(@Param("legajo") Long legajo);
}