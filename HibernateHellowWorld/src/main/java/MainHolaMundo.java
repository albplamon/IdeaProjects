import jakarta.persistence.EntityManager;
import util.GestionPersistencia;

public class MainHolaMundo {
    public static void main(String[] args) {
        EntityManager em = GestionPersistencia.crearEntityManager();
        try {
            Object resultado = em.createNativeQuery("SELECT 1")
                    .getSingleResult();
            System.out.println("PostgreSQL responde: " + resultado);
            System.out.println("¡Hola mundo desde Hibernate!");
        } finally {
            em.close();
            GestionPersistencia.cerrar();
        }
    }
}