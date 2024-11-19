package com.tpbackend.microservicio_vehiculos.services.interfaces;

import com.tpbackend.microservicio_vehiculos.entities.Prueba;
import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;

import java.time.LocalDateTime;
import java.util.List;

public interface PruebaService extends Service<Prueba, Long>{
    List<Prueba> elVehiculoEstaEnPruebaYCumpleConLosLimites(Vehiculo vehiculo, LocalDateTime fechaHora);
    List<Prueba> buscarVehiculoYFecha(Vehiculo vehiculo, LocalDateTime fechaHora);
}
