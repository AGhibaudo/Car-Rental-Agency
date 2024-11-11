package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Vehiculo;
import com.tpbackend.microservicio_pruebas.dtos.DetallesPruebaVehiculoDTO;
import com.tpbackend.microservicio_pruebas.dtos.CantKmRegistradosPorVehiculoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
    // Reportes por parte de los Incidentes - Punto 1 -> F del enunciado

    /*
    iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un
    período determinado
    */
    @Query("SELECT new com.tpbackend.microservicio_pruebas.dtos.CantKmRegistradosPorVehiculoDTO(" +
            "P.id, P.fechaHoraInicio, P.fechaHoraFin, V.patente, SUM(CAST(P.cantKmRegistrados as Double))) " +
            "FROM prueba P JOIN vehiculo V on P.vehiculo.id = V.id " +
            "WHERE P.fechaHoraInicio >= :fechaHoraInicio " +
            "AND P.fechaHoraFin <= :fechaHoraFin " +
            "AND V.id = :idVehiculo " +
            "GROUP BY V.patente")
    List<CantKmRegistradosPorVehiculoDTO> buscarKmRegistradosPorVehiculos(@Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                                                                          @Param("fechaHoraFin") LocalDateTime fechaHoraFin,
                                                                          @Param("idVehiculo") Long id);
    /*
    iv. Detalle de pruebas realizadas para un vehículo
    */
    // Utilizo B como sinonimo de Marca -> Brand -> (Marca)
    @Query("SELECT new com.tpbackend.microservicio_pruebas.dtos.DetallesPruebaVehiculoDTO(" +
            "V.id, V.patente, M.descripcion, B.nombre, P.fechaHoraInicio, P.fechaHoraFin, " +
            "I.tipoDocumento, I.documento, I.apellido, I.nombre, I.nroLicencia, I.fechaVencimientoLicencia, " +
            "E.legajo, E.apellido, E.nombre, P.comentarios) " +
            "FROM prueba P JOIN vehiculo V ON P.vehiculo.id = V.id " +
            "JOIN modelo M ON V.modelo.id = M.id " +
            "JOIN marca B ON M.marca.id = B.id " +
            "JOIN empleado E ON P.empleado.id = E.legajo " +
            "JOIN interesado I ON P.interesado.id = I.id " +
            "WHERE V.id = :idVehiculo")
    List<DetallesPruebaVehiculoDTO> buscarPruebaPorIdVehiculo(@Param("idVehiculo") Long id);
}
