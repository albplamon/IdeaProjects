
package XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearXML {
    public static void main(String[] args) {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();

            Element libros = doc.createElement("libros");
            doc.appendChild(libros);

            Element libro = doc.createElement("libro");
            libro.setAttribute("ID", "1");

            Element titulo = doc.createElement("titulo");
            titulo.setTextContent("El señor de los anillos");

            Element autor = doc.createElement("autor");
            autor.setTextContent("J.R.R. Tolkien");

            Element anio = doc.createElement("anio");
            anio.setTextContent("1954");

            libro.appendChild(titulo);
            libro.appendChild(autor);
            libro.appendChild(anio);
            libros.appendChild(libro);

            Element libro2 = doc.createElement("libro");
            libro2.setAttribute("ID", "2");

            Element titulo2 = doc.createElement("titulo");
            titulo2.setTextContent("Animal Farm");

            Element autor2 = doc.createElement("autor");
            autor2.setTextContent("George Orwell");

            Element anio2 = doc.createElement("anio");
            anio2.setTextContent("1945");

            libro2.appendChild(titulo2);
            libro2.appendChild(autor2);
            libro2.appendChild(anio2);
            libros.appendChild(libro2);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(new File("src/ficheros/nuevos_libros.xml")));

            System.out.println("XML creado correctamente");

        } catch (Exception e) {
            System.out.println("Error al crear el XML: " + e.getMessage());
        }
    }
}
