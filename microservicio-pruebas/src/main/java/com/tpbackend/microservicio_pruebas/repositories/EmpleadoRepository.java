package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Empleado;
import com.tpbackend.microservicio_pruebas.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Dentro de cada repository van las querys que son las consultas a la base de datos "agencia"
    @Query("SELECT p " +
            "FROM prueba p" +
            "WHERE p.empleado = :empleado " +
            "AND ((p.fechaHoraInicio <= :fechaHoraFin AND p.fechaHoraFin >= :fechaHoraInicio))")
    List<Prueba> findByEmpleadoYFecha(@Param("empleado") Empleado empleado,
                                      @Param("fechaHoraInicio")LocalDateTime fechaHoraInicio,
                                      @Param("fechaHoraFin") LocalDateTime fechaHoraFin);
}
