package com.tpbackend.microservicio_pruebas.repositories;

import com.tpbackend.microservicio_pruebas.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    // Acá van las querys que consultan a la BDD + Reporte.
}
