package com.tpbackend.microservicio_pruebas.services;

import com.tpbackend.microservicio_pruebas.entities.Notificacion;
import com.tpbackend.microservicio_pruebas.entities.Posicion;
import com.tpbackend.microservicio_pruebas.repositories.PosicionRepository;
import com.tpbackend.microservicio_pruebas.services.interfaces.PosicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicionServiceImpl extends ServiceImpl<Posicion, Long> implements PosicionService {

    @Autowired
    PosicionRepository posicionRepository;

    @Override
    public Posicion guardarPosicion(Posicion posicion) {
        return posicionRepository.save(posicion);
    }

    @Override
    public Posicion create(Posicion entity) {
        posicionRepository.save(entity);
        return entity;
    }

    @Override
    public Posicion update(Posicion entity) {
        posicionRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Posicion entity = findById(id);
        posicionRepository.delete(entity);
    }

    @Override
    public Posicion findById(Long id) {
        return posicionRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Posicion> findAll() {
        return posicionRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return posicionRepository.existsById(id);
    }
}
