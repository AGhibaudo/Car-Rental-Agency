package com.tpbackend.microservicio_vehiculos.context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {
    private static EntityManagerProvider emp;
    private final EntityManager entityManager;

    private EntityManagerProvider() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("agencia");
        entityManager = emf.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        if (emp == null) {
            emp = new EntityManagerProvider();
        }
        return emp.entityManager;
    }

    public static void closeEntityManager() {
        if (emp.entityManager != null) {
            emp.entityManager.close();
            emp = null;
        }
    }
}