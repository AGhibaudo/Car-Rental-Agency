package com.tpbackend.microservicio_vehiculos.services.interfaces;



public interface Service<T, K> {
    T create(T entity);
    T update(T entity);
    void delete(K id);
    T findById(K id);
    Iterable<T> findAll();
    boolean existById(K id);
}
