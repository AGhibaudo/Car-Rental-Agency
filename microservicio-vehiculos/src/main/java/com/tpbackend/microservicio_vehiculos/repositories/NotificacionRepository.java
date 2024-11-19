package com.tpbackend.microservicio_vehiculos.repositories;

import com.tpbackend.microservicio_vehiculos.entities.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}
