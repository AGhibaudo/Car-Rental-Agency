package com.tpbackend.microservicio_vehiculos.controllers;


import com.tpbackend.microservicio_vehiculos.entities.Posicion;
import com.tpbackend.microservicio_vehiculos.entities.PosicionDelVehiculo;
import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;
import com.tpbackend.microservicio_vehiculos.services.ConfiguracionServiceImpl;
import com.tpbackend.microservicio_vehiculos.services.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private ConfiguracionServiceImpl configuracionService;

    @Autowired
    private VehiculoServiceImpl vehiculoService;

    public ResponseEntity<String> status(){
        return ResponseEntity.ok("Microservicio vehiculo en ejecución...");
    }

    /*
        d. Recibir la posición actual de un vehículo y evaluar si el vehículo se encuentra en una
        prueba para revisar si está dentro de los límites establecidos. En caso de que el vehículo se
        encuentre en una prueba y haya excedido el radio permitido o ingresado a una zona peligrosa,
        se deben disparar las acciones descriptas. ATENCIÓN: NO se espera que los alumnos hagan una
        notificación real a un teléfono, sino que alcanza con almacenar la notificación en la
        base de datos; pero si un grupo desea investigar e implementar una notificación
        por mail, SMS, WhatsApp o cualquier medio, tiene libertad para hacerlo.
    */
    @PostMapping("/evaluar")
    public ResponseEntity<String> evaluarPosicion(@RequestBody PosicionDelVehiculo req){
        Posicion posicion = req.getPosicion();
        Vehiculo vehiculo = req.getVehiculo();

        String res = vehiculoService.evaluarPosicion(posicion, vehiculo);

        return ResponseEntity.ok(res);
    }
}
