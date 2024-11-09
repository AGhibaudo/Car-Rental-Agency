package com.tpbackend.microservicio_pruebas.services;


import com.tpbackend.microservicio_pruebas.entities.Empleado;
import com.tpbackend.microservicio_pruebas.entities.Prueba;
import com.tpbackend.microservicio_pruebas.repositories.EmpleadoRepository;
import com.tpbackend.microservicio_pruebas.services.interfaces.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl extends ServiceImpl<Empleado, Long> implements EmpleadoService {
    @Autowired
    EmpleadoRepository empRepository;

    @Override
    public Empleado findByLegajo(Long legajo) {
        return empRepository.findById(legajo).orElse(null);
    }

    @Override
    public boolean estaDisponible(Empleado empleado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        List<Prueba> empleadosOcupados = this.findByEmpleadoYFecha(empleado, fechaHoraInicio, fechaHoraFin);
        return empleadosOcupados.isEmpty();
    }

    @Override
    public List<Prueba> findByEmpleadoYFecha(Empleado empleado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        return empRepository.findByEmpleadoYFecha(empleado, fechaHoraInicio, fechaHoraFin);
    }

    @Override
    public Empleado create(Empleado entity) {
        empRepository.save(entity);
        return entity;
    }

    @Override
    public Empleado update(Empleado entity) {
        empRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Empleado entity = findById(id);
        empRepository.delete(entity);
    }

    @Override
    public Empleado findById(Long id) {
        return empRepository.findById(id).orElse(null);
    }

    @Override
    public List<Empleado> findAll() {
        return empRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return empRepository.existsById(id);
    }
}
