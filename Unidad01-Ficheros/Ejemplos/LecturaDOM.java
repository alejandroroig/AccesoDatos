import java.nio.file.Path;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaDOM {
    public static void main(String[] args) {
        ArrayList<Libro> libros = new ArrayList<>();

        try {
            Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos", "libreria.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(nombreFichero.toFile());
            doc.getDocumentElement().normalize();

            System.out.println("Nombre: " + doc.getElementsByTagName("nombre").item(0).getTextContent());

            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node nodoLibro = listaLibros.item(i);
                if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemLibro = (Element) nodoLibro;
                    Libro l = new Libro();
                    l.setIsbn(elemLibro.getAttribute("isbn"));
                    l.setTitulo(elemLibro.getElementsByTagName("titulo").item(0).getTextContent());
                    l.setAutor(elemLibro.getElementsByTagName("autor").item(0).getTextContent());
                    libros.add(l);
                }
            }

            for (Libro l : libros) {
                System.out.println(l.getIsbn() + ", " + l.getTitulo() + ", " + l.getAutor());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}