import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class LeerCaracter {
    public static void main(String[] args) {

        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","ficheroentrada.txt");

        // Aunque por eficiencia tiene más sentido leer un archivo por bloques,
        // también podemos forzar su lectura carácter a carácter con la clase FileReader
        System.out.println("\nLectura del fichero con FileReader");
        try (FileReader fr = new FileReader(nombreFichero.toFile())) {
            int leido;
            while ((leido = fr.read()) != -1) {
                System.out.print((char) leido);
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
