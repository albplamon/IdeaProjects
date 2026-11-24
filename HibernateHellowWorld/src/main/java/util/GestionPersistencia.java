package util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class GestionPersistencia {
    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("up3_pu");
    private GestionPersistencia() { }
    public static EntityManager crearEntityManager() {
        return EMF.createEntityManager();
    }
    public static void cerrar() {
        if (EMF.isOpen()) {
            EMF.close();
        }
    }
}