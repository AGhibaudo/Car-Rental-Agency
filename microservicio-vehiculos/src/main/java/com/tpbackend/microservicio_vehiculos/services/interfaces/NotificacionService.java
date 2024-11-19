package com.tpbackend.microservicio_vehiculos.services.interfaces;

import com.tpbackend.microservicio_vehiculos.entities.Notificacion;

public interface NotificacionService extends Service<Notificacion, Long>{
    Notificacion guardarNotificacion(Notificacion notificacion);
}
