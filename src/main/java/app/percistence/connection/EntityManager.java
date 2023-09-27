package app.percistence.connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManager {

    private static final EntityManagerFactory emf;
    private static final javax.persistence.EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("tp2");
        em = (javax.persistence.EntityManager) emf.createEntityManager();
    }

    private EntityManager() {}

    public static javax.persistence.EntityManager getEntityManager() {
        return em;
    }

    public static void closeEntityManager() {
        em.close();
        emf.close();
    }
}
