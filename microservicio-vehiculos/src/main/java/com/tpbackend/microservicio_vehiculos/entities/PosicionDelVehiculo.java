package com.tpbackend.microservicio_vehiculos.entities;

import lombok.Data;

@Data
public class PosicionDelVehiculo {
    private Posicion posicion;
    private Vehiculo vehiculo;
}
