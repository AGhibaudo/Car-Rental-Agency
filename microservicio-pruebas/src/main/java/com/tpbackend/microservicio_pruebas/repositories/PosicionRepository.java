package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Long> {
}
