import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class AnadirLineas {
    public static void imprimirFichero(Path rutaFichero) {
        try (Stream<String> lineas = Files.lines(rutaFichero)) {
            lineas.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","ficherosalida2.txt");
        ArrayList<String> listaCadenas = new ArrayList<>();
        listaCadenas.add("En este fichero de ejemplo tenemos 6 líneas.");
        listaCadenas.add("Esta será la segunda,");
        listaCadenas.add("esta la tercera");
        listaCadenas.add("esta la cuarta, ya solo quedan 2");
        listaCadenas.add("esta es la quinta y penúltima...");
        listaCadenas.add("y con esta última se acaba el fichero.");

        // Opción 1: Files.writeString
        // Importante la opción APPEND, aunque si el fichero no existe se lanzará una excepción
        // por lo que también añadimos la opción CREATE
        // Además, no hace un salto de línea automáticamente antes de escribir como quizás podría interesarnos
        System.out.println("\nEscritura al final del fichero con Files.writeString");
        try {
            Files.writeString(nombreFichero, listaCadenas.get(0), CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 2: Files.write
        System.out.println("\nEscritura al final del fichero con Files.write");
        try {
            Files.write(nombreFichero, ("\n" + listaCadenas.get(1)).getBytes(), CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 3: FileWriter
        // FileWriter si el fichero no existe lo crea, por lo que el comportamiento
        // es distinto ante la ausencia de fichero respecto a las anteriores
        System.out.println("\nEscritura al final del fichero con FileWriter");
        try (FileWriter fr = new FileWriter(nombreFichero.toFile(), true)) {
            fr.write("\n" + listaCadenas.get(2) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 4: BufferedWriter
        // Aquí veremos la versión con java.io
        System.out.println("\nEscritura al final del fichero con BufferedWriter (java.io)");
        try (FileWriter fw = new FileWriter(nombreFichero.toFile(), true);
             BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(listaCadenas.get(3));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);


        // Opción 5: BufferedWriter
        // Aquí veremos la versión con java.nio, que también requiere de añadir StandardOpenOption.APPEND
        System.out.println("\nEscritura al final del fichero con BufferedWriter (java.nio)");
        try (BufferedWriter bw = Files.newBufferedWriter(nombreFichero, CREATE, APPEND)){
            bw.write(listaCadenas.get(4));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);

        // Opción 5: PrintWriter
        // Aquí veremos la versión con java.nio
        System.out.println("\nEscritura al final del fichero con PrintWriter");
        try (FileWriter fw = new FileWriter(nombreFichero.toFile(), true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(listaCadenas.get(5));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imprimirFichero(nombreFichero);
    }
}
