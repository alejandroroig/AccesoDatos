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
            // DocumentBuilderFactory es una clase especial para crear parsers
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // DocumentBuilder define el parser DOM que se va a utilizar
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Document es el objeto que contiene la lectura completa del XML
            Document doc = dBuilder.parse(nombreFichero.toFile());
            // Ahora doc tiene el documento en memoria
            doc.getDocumentElement().normalize();

            System.out.println("Nombre: " + doc.getElementsByTagName("nombre").item(0).getTextContent());

            // NodeList es la lista que contiene todos los nodos hijos de un nodo
            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                // Node representa a cualquier nodo de un árbol
                Node nodoLibro = listaLibros.item(i);
                if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                    // Element es un tipo de nodo que representa un elemento del XML
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