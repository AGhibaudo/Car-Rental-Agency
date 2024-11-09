package com.tpbackend.microservicio_pruebas.services.interfaces;

import com.tpbackend.microservicio_pruebas.entities.Notificacion;

import java.util.List;

public interface Service<T, K> {
    T create(T entity);
    T update(T entity);
    void delete(K id);
    T findById(K id);
    List<T> findAll();
    boolean existById(K id);
}
