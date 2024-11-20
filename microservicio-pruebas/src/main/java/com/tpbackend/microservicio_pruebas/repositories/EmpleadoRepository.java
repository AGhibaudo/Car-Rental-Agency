package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Empleado;
import com.tpbackend.microservicio_pruebas.entities.Prueba;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
    // Acá hacemos la consulta para saber si el empleado esta disponible.
    @Query("SELECT P " +
            "FROM Prueba P " +
            "WHERE P.empleado = :empleado " +
            "AND ((P.fechaHoraInicio <= :fechaHoraFin AND P.fechaHoraFin >= :fechaHoraInicio))")
    List<Prueba> findByEmpleadoYFecha(@Param("empleado") Empleado empleado,
                                      @Param("fechaHoraInicio")LocalDateTime fechaHoraInicio,
                                      @Param("fechaHoraFin") LocalDateTime fechaHoraFin);
}
