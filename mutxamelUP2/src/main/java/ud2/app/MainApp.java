package ud2.app;
import ud2.dao.LoginDAO;
import ud2.dao.SoftwareAuditadoDAO;
import ud2.model.Login;
import ud2.model.SoftwareAuditado;
import java.util.List;
public class MainApp {
    public static void main(String[] args) {
        LoginDAO loginDAO = new LoginDAO();
        SoftwareAuditadoDAO softwareDAO = new SoftwareAuditadoDAO();
        // 1. Insertamos o verificamos a la analista Silvia en PostgreSQL
        if (loginDAO.buscarPorUserName("Silvia") == null) {
            Login silvia = new Login("Silvia", "silvia@silvia.es");
            if (loginDAO.insertarAnalista(silvia)) {
                System.out.println("Analista Silvia registrada con éxito en mutxamel_pg.");
            }
        }
        // 2. Listamos todos los analistas persistidos en la tabla 'login'
        System.out.println("\n=== PLANTILLA DE ANALISTAS (TABLA LOGIN) ===");
        List<Login> analistas = loginDAO.obtenerTodos();
        for (Login a : analistas) {
            System.out.println(a);
        }
        // 3. Listamos el inventario completo de software auditado
        System.out.println("\n=== INVENTARIO DE SOFTWARE AUDITADO ===");
        List<SoftwareAuditado> inventario = softwareDAO.obtenerTodos();
        for (SoftwareAuditado app : inventario) {
            System.out.println(app);
        }
        // 4. Consultamos aplicaciones críticas (nivel de exposición >= 4)
        System.out.println("\n=== ALERTA SEC-OPS: SOFTWARE CRÍTICO (RIESGO >= 4) ===");
        List<SoftwareAuditado> criticas = softwareDAO.buscarPorRiesgoMinimo(4);
        for (SoftwareAuditado app : criticas) {
            System.out.println(app);
        }
    }
}