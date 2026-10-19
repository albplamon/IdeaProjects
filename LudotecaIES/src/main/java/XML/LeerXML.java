package XML;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class LeerXML {
    public static void main(String[] args) {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(new File("src/ficheros/nuevos_libros.xml"));
            NodeList titulos = doc.getElementsByTagName("titulo");
            NodeList autor = doc.getElementsByTagName("autor");
            NodeList anio = doc.getElementsByTagName("anio");
            for (int i = 0; i < titulos.getLength(); i++) {
                System.out.println(((Element) doc.getElementsByTagName("libro").item(i)).getAttribute("ID"));
                System.out.println(titulos.item(i).getTextContent());
                System.out.println(autor.item(i).getTextContent());
                System.out.println(anio.item(i).getTextContent());
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error al leer el XML: " + e.getMessage());
        }
    }
}