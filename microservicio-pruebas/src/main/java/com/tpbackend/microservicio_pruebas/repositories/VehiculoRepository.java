package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Vehiculo;
import com.tpbackend.microservicio_pruebas.dtos.DetallesPruebaVehiculoDTO;
import com.tpbackend.microservicio_pruebas.dtos.CantKmRegistradosPorVehiculoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo,Long> {
    // Reportes por parte de los Incidentes - Punto 1 -> F del enunciado

    /*
    iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un
    período determinado
    */
    @Query("SELECT new com.tpbackend.microservicio_pruebas.dtos.CantKmRegistradosPorVehiculoDTO(" +
            "p.id, p.fechaHoraInicio, p.fechaHoraFin, v.patente, SUM(CAST(p.cantKmRegistrados as Double))) " +
            "FROM Prueba p JOIN Vehiculo v on p.vehiculo.id = v.id " +
            "WHERE p.fechaHoraInicio >= :fechaHoraInicio " +
            "AND p.fechaHoraFin <= :fechaHoraFin " +
            "AND v.id = :idVehiculo " +
            "GROUP BY v.patente")
    List<CantKmRegistradosPorVehiculoDTO> buscarKmRegistradosPorVehiculos(@Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                                                          @Param("fechaHoraFin") LocalDateTime fechaHoraFin,
                                                                          @Param("idVehiculo") Long id);
    /*
    iv. Detalle de pruebas realizadas para un vehículo
    */
    // Utilizo B como sinonimo de Marca -> Brand -> (Marca)
    @Query("SELECT new com.tpbackend.microservicio_pruebas.dtos.DetallesPruebaVehiculoDTO(" +
            "v.id, v.patente, m.descripcion, b.nombre, p.fechaHoraInicio, p.fechaHoraFin, " +
            "i.tipoDocumento, i.documento, i.apellido, i.nombre, i.nroLicencia, i.fechaVencimientoLicencia, " +
            "e.legajo, e.apellido, e.nombre, p.comentarios) " +
            "FROM Prueba p JOIN Vehiculo v ON p.vehiculo.id = v.id " +
            "JOIN Modelo m ON v.modelo.id = m.id " +
            "JOIN Marca b ON m.marca.id = b.id " +
            "JOIN Empleado e ON p.empleado.id = e.legajo " +
            "JOIN Interesado i ON p.interesado.id = i.id " +
            "WHERE v.id = :idVehiculo")
    List<DetallesPruebaVehiculoDTO> buscarPruebaPorIdVehiculo(@Param("idVehiculo") Long id);
}
