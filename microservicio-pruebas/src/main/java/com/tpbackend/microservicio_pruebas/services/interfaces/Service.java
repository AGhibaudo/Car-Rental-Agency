package com.tpbackend.microservicio_pruebas.services.interfaces;

public interface Service<T, K> {
    T create(T entity);
    T update(T entity);
    void delete(K id);
    Iterable<T> findAll();
    boolean existById(K id);
}
