package com.tpbackend.microservicio_pruebas.services.interfaces;

import com.tpbackend.microservicio_pruebas.entities.Posicion;

public interface PosicionService extends Service<Posicion, Long> {
    Posicion guardarPosicion(Posicion posicion);
}
