package com.tpbackend.microservicio_vehiculos.repositories;

import com.tpbackend.microservicio_vehiculos.entities.Prueba;
import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {

    @Query("SELECT P " +
            "FROM Prueba P JOIN Vehiculo V ON P.vehiculo.id = V.id " +
            "JOIN Posicion Pos ON V.posicion.id = Pos.id " +
            "WHERE :fechaHora BETWEEN P.fechaHoraInicio AND P.fechaHoraFin AND P.activa = true")
    List<Prueba> buscarPorVehiculoYFecha(@Param("vehiculo") Vehiculo vehiculo,
                                         @Param("fechaHora")LocalDateTime fechaHora);
}