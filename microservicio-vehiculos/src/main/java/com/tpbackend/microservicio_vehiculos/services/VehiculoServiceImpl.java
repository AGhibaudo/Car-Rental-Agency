package com.tpbackend.microservicio_vehiculos.services;

import com.tpbackend.microservicio_vehiculos.entities.*;
import com.tpbackend.microservicio_vehiculos.repositories.VehiculoRepository;
import com.tpbackend.microservicio_vehiculos.services.interfaces.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehiculoServiceImpl extends ServiceImpl<Vehiculo, Long> implements VehiculoService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    private ConfiguracionServiceImpl configuracionService;

    @Autowired
    private PruebaServiceImpl pruebaService;

    @Autowired
    private NotificacionServiceImpl notificacionService;


    @Override
    public String evaluarPosicion(Posicion posicion, Vehiculo vehiculo) {
        LocalDateTime fechaHora = posicion.getFechaHora();
        List<Prueba> pruebas = pruebaService.elVehiculoEstaEnPruebaYCumpleConLosLimites(vehiculo, fechaHora);
        if(!pruebas.isEmpty()) {
            Prueba prueba = pruebas.get(0);
            Empleado empleado = prueba.getEmpleado();
            double latitud = posicion.getLatitud();
            double longitud = posicion.getLongitud();

            ConfiguracionResponse configuracionAPI = configuracionService.obtenerConfiguracion();
            double latAgencia = configuracionAPI.getCoordendaAgencia().getLat();
            double lonAgencia = configuracionAPI.getCoordendaAgencia().getLon();
            double radioAdmitidoKm = configuracionAPI.getRadioAdmitidoEnKm();

            double radioLatitudGrados = radioAdmitidoKm / 111.0;
            double radioLongitudGrados = radioAdmitidoKm / (111.0 * Math.cos(Math.toRadians(latAgencia)));

            Long telefonoInteresado = prueba.getInteresado().getTelefonoContacto();

            // Acá comprobamos si la posición está dentro del radio estipulado.
            if (latitud < (latAgencia - radioLatitudGrados) || latitud > (latAgencia + radioLatitudGrados) ||
                    longitud < (lonAgencia - radioLongitudGrados) || longitud > (lonAgencia + radioLongitudGrados)) {

                Notificacion notificacion = new Notificacion();
                notificacion.setLegajo(empleado.getLegajo());
                notificacion.setDescripcion("El vehículo se encuentra fuera del radio permitido. Debe regresar.");
                notificacion.setTelefonoInteresado(telefonoInteresado);
                notificacionService.guardarNotificacion(notificacion);

                return "El vehiculo se encuentra fuera del radio permitido de 5 km alrededor de la Agencia.";
            }

            // Comprobar las zonas restringidas particulares !
            List<ZonaRestringida> zonasRestringidas = configuracionAPI.getZonasRestringidas();
            for (ZonaRestringida zona : zonasRestringidas) {
                double latNoroeste = zona.getNoroeste().getLat();
                double lonNoroeste = zona.getNoroeste().getLon();
                double latSureste = zona.getSureste().getLat();
                double lonSureste = zona.getSureste().getLon();

                // Comprobar si la posición está dentro de la zona restringida
                if (latitud <= latNoroeste && latitud >= latSureste && longitud <= lonSureste && longitud >= lonNoroeste) {
                    prueba.getInteresado().setRestringido(true);
                    Notificacion notificacion = new Notificacion();
                    notificacion.setLegajo(empleado.getLegajo());
                    notificacion.setDescripcion("El vehículo se encuentra en una zona restringida. Deberá regresar.");
                    notificacion.setTelefonoInteresado(telefonoInteresado);
                    notificacionService.guardarNotificacion(notificacion);
                    return "El vehículo se encuentra en una prueba, pero su posición está dentro de una zona restringida.";
                }
            }

            // En caso de pasar todas las veríficaciones.
            return "El vehículo se encuentra en una prueba y además su posición no se encuentra en una zona restringida ni fuera del radio permitido.";
        }
        return "El vehículo no se encuentra en una ninguna prueba.";
    }

    @Override
    public Vehiculo create(Vehiculo entity) {
        vehiculoRepository.save(entity);
        return entity;
    }

    @Override
    public Vehiculo update(Vehiculo entity) {
        vehiculoRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Vehiculo vehiculo = findById(id);
        vehiculoRepository.delete(vehiculo);
    }

    @Override
    public Vehiculo findById(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return vehiculoRepository.existsById(id);
    }
}
