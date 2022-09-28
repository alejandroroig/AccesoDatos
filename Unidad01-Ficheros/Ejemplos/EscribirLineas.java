import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class EscribirLineas {
    public static void imprimirFichero(Path rutaFichero) {
        try (Stream<String> lineas = Files.lines(rutaFichero)) {
            lineas.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","ficherosalida.txt");
        ArrayList<String> listaCadenas = new ArrayList<>();
        listaCadenas.add("En este fichero de ejemplo tenemos 6 líneas.");
        listaCadenas.add("Esta será la segunda,");
        listaCadenas.add("esta la tercera");
        listaCadenas.add("esta la cuarta, ya solo quedan 2");
        listaCadenas.add("esta es la quinta y penúltima...");
        listaCadenas.add("y con esta última se acaba el fichero.");

        // Opción 1: Files.writeString
        // Si nos fijamos, lo que está ocurriendo es que la escritura de cada línea está
        // sobreescribiendo lo escrito anteriormente.
        System.out.println("\nEscritura del fichero con Files.writeString");
        try {
            for (String cadena : listaCadenas)
                Files.writeString(nombreFichero, cadena);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 2: Files.write
        // En vez del string propiamente dicho, el método getBytes() lo convierte en un
        // array de bytes, que es lo que se escribe.
        // También sobreescribe lo escrito anteriormente
        System.out.println("\nEscritura del fichero con Files.write");
        try {
            for (String cadena : listaCadenas)
                Files.write(nombreFichero, cadena.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opciones 1 y 2 resueltas:
        // Si quiero escribir una secuencia de Strings en un momento dado, y esos Strings
        // los tengo almacenados en una colleción como un ArrayList, puedo unirlos y convertirlos
        // en un solo String previamente
        System.out.println("\nEscritura correcta del fichero con Files.write");
        try {
            Files.write(nombreFichero, String.join("\n", listaCadenas).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 3: FileWriter
        // Al ser FileWriter de la librería java.io, es necesario convertir la
        // instancia de Path de java.nio a una de File de java.io con el método toFile()
        System.out.println("\nEscritura del fichero con FileWriter");
        try (FileWriter fr = new FileWriter(nombreFichero.toFile())){
            for (String cadena : listaCadenas)
                fr.write(cadena + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 4: BufferedWriter
        // Aquí veremos la versión con java.io
        System.out.println("\nEscritura del fichero con BufferedWriter (java.io)");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero.toFile()))){
            for (String cadena : listaCadenas) {
                bw.write(cadena);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);


        // Opción 5: BufferedWriter
        // Aquí veremos la versión con java.nio
        System.out.println("\nEscritura del fichero con BufferedWriter (java.nio)");
        try (BufferedWriter bw = Files.newBufferedWriter(nombreFichero)){
            for (String cadena : listaCadenas) {
                bw.write(cadena);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 5: PrintWriter
        // Aquí veremos la versión con java.nio
        System.out.println("\nEscritura del fichero con PrintWriter");
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero.toFile()))){
            for (String cadena : listaCadenas)
                pw.println(cadena);
            imprimirFichero(nombreFichero);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);
    }
}
