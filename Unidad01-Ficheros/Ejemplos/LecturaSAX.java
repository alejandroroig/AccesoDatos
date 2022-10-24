import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class LecturaSAX {
    // Lista de libros
    ArrayList<Libro> libros = new ArrayList<>();

    // Almacenamiento de elementos y objeto Libro
    Stack<String> elements = new Stack<>();
    Stack<Libro> objetos = new Stack<>();

    public static void main(String[] args) {
        LecturaSAX ficheroXML = new LecturaSAX();
        ficheroXML.procesarXML();
    }

    public void procesarXML() {
        try {
            // SAXParserFactory es una clase especial para crear parsers
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            // SAXParser define el parser SAX que se va a utilizar
            SAXParser saxParser = saxParserFactory.newSAXParser();

            String nombreFichero = "Unidad01-Ficheros\\\\Ejemplos\\\\libreria.xml";

            // DefaultHandler es una clase abstracta que se debe implementar y contiene llamadas a los eventos
            // Parse analizará el fichero deseado llamando a startElement, endElement y characters
            saxParser.parse(nombreFichero, new DefaultHandler() {

                // Método que se llama al encontrar inicio de etiqueta : '<'
                // Aquí podemos leer los atributos
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    elements.push(qName);
                    if (Objects.equals(qName, "libro")) {
                        Libro libro = new Libro();
                        libro.setIsbn(attributes.getValue("isbn"));
                        objetos.push(libro);
                        libros.add(libro);
                    }
                }

                // Obtiene los datos entre '<' y '>'
                // Permite leer los
                public void characters(char ch[], int start, int length) throws SAXException {
                    String value = new String(ch, start, length);
                    if (value.length() == 0) {
                        return;
                    }
                    if ("titulo".equals(currentElement())) {
                        Libro libro = objetos.peek();
                        libro.setTitulo(value);
                    } else if ("autor".equals(currentElement())) {
                        Libro libro = objetos.peek();
                        libro.setAutor(value);
                    }
                }

                // Llamado al encontrar un fin de etiqueta: '>'
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    elements.pop();
                }
            });

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.getStackTrace();
        }

        for (Libro libro : libros) {
            System.out.println(libro.getIsbn() + ", " + libro.getTitulo() + ", " + libro.getAutor());
        }
    }

    private String currentElement() {
        return elements.peek();
    }
}
