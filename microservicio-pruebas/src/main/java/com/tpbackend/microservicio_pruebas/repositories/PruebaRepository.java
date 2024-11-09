package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Prueba;
import com.tpbackend.microservicio_pruebas.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    // Acá van las querys que consultan a la BDD + Reporte.
    @Query("SELECT p " +
            "FROM prueba p " +
            "WHERE p.vehiculo = :vehiculo AND " +
            "((p.fechaHoraInicio <= :fechaHoraFin " +
            "AND p.fechaHoraFin >= :fechaHoraInicio))")
    List<Prueba> buscarVehiculoYFecha(@Param("vehiculo") Vehiculo vehiculo,
                                      @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                      @Param("fechaHoraFin") LocalDateTime fechaHoraFin);

    @Query("SELECT p " +
            "FROM prueba p " +
            "WHERE p.fechaHoraInicio <= :fechaActual AND " +
            "p.fechaHoraFin >= :fechaActual")
    List<Prueba> buscarPruebasEnCurso(@Param("fechaActual") LocalDateTime fechaActual);
}
