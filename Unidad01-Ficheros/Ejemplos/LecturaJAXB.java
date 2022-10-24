import java.nio.file.Path;
import java.util.ArrayList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;



//
// Añadir dependencias:
// jakarta.xml.bind-api 3.0.0
// jaxb-impl 3.0.0
//
public class LecturaJAXB {
    public static void main(String[] args)  {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","libreria.xml");
        JAXBContext context = null;

        try {
            context = JAXBContext.newInstance(Libreria.class);
            Unmarshaller jaxbUnmarshaller  = context.createUnmarshaller();
            Libreria libreria = (Libreria) jaxbUnmarshaller.unmarshal(nombreFichero.toFile());

            System.out.println("Nombre: " + libreria.getNombre());

            ArrayList<Libro> libros = libreria.getLibros();

            for(Libro l: libros){
                System.out.println(l.getIsbn() + ", " + l.getTitulo() + ", " + l.getAutor());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

