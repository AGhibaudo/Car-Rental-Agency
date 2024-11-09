package com.tpbackend.microservicio_pruebas.services.interfaces;

import com.tpbackend.microservicio_pruebas.entities.Notificacion;
import com.tpbackend.microservicio_pruebas.entities.NotificacionTelefono;

public interface NotificacionTelefonoService extends Service<Notificacion, Long> {
    void guardarNotificacion(NotificacionTelefono notificacionTelefono);
}