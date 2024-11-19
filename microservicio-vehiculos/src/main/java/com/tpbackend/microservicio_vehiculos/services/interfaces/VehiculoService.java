package com.tpbackend.microservicio_vehiculos.services.interfaces;

import com.tpbackend.microservicio_vehiculos.entities.Posicion;
import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;

public interface VehiculoService extends Service<Vehiculo, Long>{
    String evaluarPosicion(Posicion posicion, Vehiculo vehiculo);
}
