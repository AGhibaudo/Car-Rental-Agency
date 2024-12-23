package com.tpbackend.microservicio_vehiculos.repositories;

import com.tpbackend.microservicio_vehiculos.entities.Prueba;
import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PruebaRepository extends CrudRepository<Prueba, Long> {

    @Query("SELECT p FROM Prueba p, Vehiculo v, Posicion pos " +
            "WHERE v.posicion.id = pos.id AND v.id = p.vehiculo.id AND p.vehiculo = :vehiculo AND " +
            ":fechaHora BETWEEN p.fechaHoraInicio AND p.fechaHoraFin AND p.activa = true")
    List<Prueba> buscarPorVehiculoYFecha(@Param("vehiculo") Vehiculo vehiculo,
                                         @Param("fechaHora")LocalDateTime fechaHora);
}