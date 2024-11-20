package com.tpbackend.microservicio_vehiculos.repositories;

import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {
}
