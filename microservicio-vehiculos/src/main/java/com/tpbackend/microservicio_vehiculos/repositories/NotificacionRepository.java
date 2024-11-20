package com.tpbackend.microservicio_vehiculos.repositories;

import com.tpbackend.microservicio_vehiculos.entities.Notificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {
}
