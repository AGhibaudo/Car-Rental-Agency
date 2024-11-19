package com.tpbackend.microservicio_vehiculos.services;

import com.tpbackend.microservicio_vehiculos.entities.Notificacion;
import com.tpbackend.microservicio_vehiculos.repositories.NotificacionRepository;
import com.tpbackend.microservicio_vehiculos.services.interfaces.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServiceImpl extends ServiceImpl<Notificacion, Long>  implements NotificacionService {

    @Autowired
    NotificacionRepository notificacionRepository;


    @Override
    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public Notificacion create(Notificacion entity) {
        notificacionRepository.save(entity);
        return entity;
    }

    @Override
    public Notificacion update(Notificacion entity) {
        notificacionRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Notificacion notificacion = findById(id);
        notificacionRepository.delete(notificacion);
    }

    @Override
    public Notificacion findById(Long id) {
        return notificacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return notificacionRepository.existsById(id);
    }
}
