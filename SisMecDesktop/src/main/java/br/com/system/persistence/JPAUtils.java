package br.com.system.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author crhobus
 */
public class JPAUtils {

    private static JPAUtils instance;
    private final EntityManagerFactory entityManagerFactory;

    private JPAUtils() {
        entityManagerFactory = createFactory();
    }

    public static JPAUtils getInstance() {
        if (instance == null) {
            instance = new JPAUtils();
        }
        return instance;
    }

    private EntityManagerFactory createFactory() {
        return Persistence.createEntityManagerFactory("SystemMecDBPU");
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager(EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }
}
