package com.tpbackend.microservicio_vehiculos.repositories;

import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
