package com.tpbackend.microservicio_vehiculos.services;

import com.tpbackend.microservicio_vehiculos.entities.Posicion;
import com.tpbackend.microservicio_vehiculos.entities.Prueba;
import com.tpbackend.microservicio_vehiculos.entities.Vehiculo;
import com.tpbackend.microservicio_vehiculos.repositories.PruebaRepository;
import com.tpbackend.microservicio_vehiculos.services.interfaces.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaServiceImpl extends ServiceImpl<Prueba, Long> implements PruebaService {

    @Autowired
    PruebaRepository pruebaRepository;

    @Override
    public List<Prueba> elVehiculoEstaEnPruebaYCumpleConLosLimites(Vehiculo vehiculo, LocalDateTime fechaHora) {
        return this.buscarVehiculoYFecha(vehiculo, fechaHora);
    }

    @Override
    public List<Prueba> buscarVehiculoYFecha(Vehiculo vehiculo, LocalDateTime fechaHora) {
        return pruebaRepository.buscarPorVehiculoYFecha(vehiculo, fechaHora);
    }


    @Override
    public Prueba create(Prueba entity) {
        pruebaRepository.save(entity);
        return entity;
    }

    @Override
    public Prueba update(Prueba entity) {
        pruebaRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Prueba prueba = findById(id);
        pruebaRepository.delete(prueba);
    }

    @Override
    public Prueba findById(Long id) {
        return pruebaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Prueba> findAll() {
        return pruebaRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return pruebaRepository.existsById(id);
    }
}
