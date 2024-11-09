package com.tpbackend.mspruebas.persistence;

// ESTO MODIFIQUENLO UN TOQUE PORQ ES BASTENTE PARTICULAR !!

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Aplicando el patron singleton
public class EMProvider {
    private static EMProvider emp;
    private final EntityManager em;

    // Iniciar EntityManager
    private EMProvider() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("agencia");
        em = emf.createEntityManager();
    }

    // Crear la instancia
    public static EntityManager getEntityManager() {
        if (emp == null) {
            emp = new EMProvider();
        }

        return emp.em;
    }

    // Cerrar conexion
    public static void closeEntityManager() {
        if (emp.em != null) {
            emp.em.close();
            emp = null;
        }
    }
}