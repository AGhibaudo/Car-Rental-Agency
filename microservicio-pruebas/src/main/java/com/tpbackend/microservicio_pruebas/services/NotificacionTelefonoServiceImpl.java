package com.tpbackend.microservicio_pruebas.services;

import com.tpbackend.microservicio_pruebas.entities.Notificacion;
import com.tpbackend.microservicio_pruebas.entities.NotificacionTelefono;
import com.tpbackend.microservicio_pruebas.repositories.NotificacionTelefonoRepository;
import com.tpbackend.microservicio_pruebas.services.interfaces.NotificacionTelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionTelefonoServiceImpl extends ServiceImpl<Notificacion, Long> implements NotificacionTelefonoService {

    @Autowired
    NotificacionTelefonoRepository notificacionTelefonoRepository;

    // Este guardar notificacion crea una lista de telefonos en la que se guarda una nueva notificación.
    @Override
    public void guardarNotificacion(NotificacionTelefono notificacionTelefono) {
        List<Long> telefonos = notificacionTelefono.getTelefonos();
        Notificacion notificacion = notificacionTelefono.getNotificacion();

        for (long telefono : telefonos) {
            Notificacion nuevaNotificacion = new Notificacion();
            nuevaNotificacion.setDescripcion(notificacion.getDescripcion());
            nuevaNotificacion.setLegajo(notificacion.getLegajo());
            nuevaNotificacion.setTelefonoInteresado(nuevaNotificacion.getTelefonoInteresado());
            notificacionTelefonoRepository.save(nuevaNotificacion);
        }
    }

    @Override
    public Notificacion create(Notificacion entity) {
        notificacionTelefonoRepository.save(entity);
        return entity;
    }

    @Override
    public Notificacion update(Notificacion entity) {
        notificacionTelefonoRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Notificacion entity = findById(id);
        notificacionTelefonoRepository.delete(entity);
    }

    @Override
    public Notificacion findById(Long id) {
        return notificacionTelefonoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionTelefonoRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return notificacionTelefonoRepository.existsById(id);
    }
}