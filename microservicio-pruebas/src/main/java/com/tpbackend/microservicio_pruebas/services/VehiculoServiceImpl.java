package com.tpbackend.microservicio_pruebas.services;

import com.tpbackend.microservicio_pruebas.dtos.CantKmRegistradosPorVehiculoDTO;
import com.tpbackend.microservicio_pruebas.dtos.DetallesPruebaVehiculoDTO;
import com.tpbackend.microservicio_pruebas.entities.Vehiculo;
import com.tpbackend.microservicio_pruebas.repositories.VehiculoRepository;
import com.tpbackend.microservicio_pruebas.services.interfaces.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehiculoServiceImpl extends ServiceImpl<Vehiculo, Long> implements VehiculoService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    // Aca vamos a crear ambos reportes, por ende llamamos a la Query del Repositorio Vehiculo
    // Llamada al primer DTO p/ Vehiculo
    public List<CantKmRegistradosPorVehiculoDTO> buscarKmRegistradosPorVehiculos(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Long idVehiculo){
        return vehiculoRepository.buscarKmRegistradosPorVehiculos(fechaHoraInicio, fechaHoraInicio, idVehiculo);
    }

    // Llamada al Segundo DTO p/ Vehiculo
    public List<DetallesPruebaVehiculoDTO> buscarPruebaPorIdVehiculo(Long idVehiculo){
        return vehiculoRepository.buscarPruebaPorIdVehiculo(idVehiculo);
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
        Vehiculo entity = findById(id);
        vehiculoRepository.delete(entity);
    }

    @Override
    public Vehiculo findById(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return vehiculoRepository.existsById(id);
    }
}
