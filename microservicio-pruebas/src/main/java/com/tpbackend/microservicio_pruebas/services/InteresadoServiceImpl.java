package com.tpbackend.microservicio_pruebas.services;

import com.tpbackend.microservicio_pruebas.entities.Interesado;
import com.tpbackend.microservicio_pruebas.entities.Notificacion;
import com.tpbackend.microservicio_pruebas.repositories.InteresadoRepository;
import com.tpbackend.microservicio_pruebas.services.interfaces.InteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresadoServiceImpl extends ServiceImpl<Interesado, Long> implements InteresadoService {

    @Autowired
    InteresadoRepository interesadoRepository;

    @Override
    public Interesado create(Interesado entity) {
        interesadoRepository.save(entity);
        return entity;
    }

    @Override
    public Interesado update(Interesado entity) {
        interesadoRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Interesado entity = findById(id);
        interesadoRepository.delete(entity);
    }

    @Override
    public Interesado findById(Long id) {
        return interesadoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Interesado> findAll() {
        return interesadoRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return interesadoRepository.existsById(id);
    }
}