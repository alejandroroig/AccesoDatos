import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class LeerLineas {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","ficheroentrada.txt");

        // Opción 1: Files.lines. Introducida en Java 8, funciona bien leyendo ficheros de texto
        // tanto grandes como pequeños. Devuelve un stream y cierra los recursos por sí misma.
        // Files.lines acepta como 2º parámetro el charset, por defecto StandardCharsets.UTF_8.
        // En caso de querer una lista de String podríamos transformar el stream con .collect
        System.out.println("\nLectura del fichero con Files.lines");
        try (Stream<String> lineas = Files.lines(nombreFichero)) {
            lineas.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Opción 2: Files.readString. Introducida en Java 11, funciona en archivos pequeños (< 2 GB)
        // o lanzará excepción java.lang.OutOfMemoryError: Required array size too large
        // El fichero se lee a un String. También acepta como 2º parámetro el charset, por defecto UTF-8
        System.out.println("\nLectura del fichero con Files.readString");
        try {
            String fichero = Files.readString(nombreFichero);
            System.out.println(fichero);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Opción 3: Files.readAllLines. En Java 8. Puede lanzar excepción
        // java.lang.OutOfMemoryError: Java heap space si el tamaño es mayor
        // que el de la memoria de la JVM
        System.out.println("\nLectura del fichero con Files.readAllLines");
        try {
            List<String> lines = Files.readAllLines(nombreFichero);
            lines.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Opción 4: BufferedReader para versiones de Java 7 o anteriores
        System.out.println("\nLectura del fichero con BufferedReader");
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero.toFile()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Opción 5: BufferedReader para versiones de Java 8 y superiores
        System.out.println("\nLectura del fichero con BufferedReader (Java 8+)");
        try (BufferedReader br = Files.newBufferedReader(nombreFichero)) {
            br.lines().forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Opción 6: Scanner es muy lento para leer ficheros grandes
        System.out.println("\nLectura del fichero con Scanner");
        try (Scanner sc = new Scanner(new FileReader(nombreFichero.toFile()))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                System.out.println(linea);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
