import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class SolEj04 {

    // Programa que pide frases al usuario y las almacena en frases.txt
    public static void ejercicio1() throws IOException {
        Scanner sc = new Scanner(System.in);
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejercicios","frases.txt");
        List<String> listaLineas = new ArrayList<>();

        String linea;
        System.out.println("\nEjercicio 1");
        System.out.println("Escribe una frase (vacía para salir): ");
        do {
            linea = sc.nextLine();
            if (!linea.isEmpty())
                listaLineas.add(linea);
        } while(!linea.isEmpty());

        try {
            Files.writeString(nombreFichero, String.join("\n", listaLineas));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Files.lines(nombreFichero).forEach(System.out::println);
    }

    // Programa que pide frases al usuario y las añade en anotaciones.txt
    public static void ejercicio2() throws IOException {
        Scanner sc = new Scanner(System.in);
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejercicios","anotaciones.txt");
        List<String> listaLineas = new ArrayList<>();

        String linea;
        System.out.println("\nEjercicio 2");
        System.out.println("Escribe una frase (vacía para salir): ");
        do {
            linea = sc.nextLine();
            if (!linea.isEmpty())
                listaLineas.add(linea);
        } while(!linea.isEmpty());

        try {
            Files.writeString(nombreFichero, String.join("\n", listaLineas), CREATE, APPEND);
            Files.writeString(nombreFichero, "\n", CREATE, APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Files.lines(nombreFichero).forEach(System.out::println);

    }

    // Programa que pide frases al usuario y las añade en anotaciones.txt con fecha y hora
    public static void ejercicio3() throws IOException {
        Scanner sc = new Scanner(System.in);
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejercicios","anotaciones.txt");
        List<String> listaLineas = new ArrayList<>();

        String linea;
        System.out.println("\nEjercicio 3");
        System.out.println("Escribe una frase (vacía para salir): ");
        do {
            linea = sc.nextLine();
            if (!linea.isEmpty())
                listaLineas.add(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " - " + linea);
        } while(!linea.isEmpty());

        try {
            Files.writeString(nombreFichero, String.join("\n", listaLineas), CREATE, APPEND);
            Files.writeString(nombreFichero, "\n", CREATE, APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Files.lines(nombreFichero).forEach(System.out::println);

    }
    public static void main(String[] args) throws IOException {
        ejercicio1();
        ejercicio2();
        ejercicio3();
    }
}
