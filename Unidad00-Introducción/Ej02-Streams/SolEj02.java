import java.util.*;
import java.util.stream.Collectors;

public class SolEj02 {
    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<>();

        libros.add(new Libro("El Señor de los Anillos", 800, "J.R.R. Tolkien"));
        libros.add(new Libro("El Hobbit", 350, "J.R.R. Tolkien"));
        libros.add(new Libro("Cabo Trafalgar", 320, "Arturo Pérez Reverte"));
        libros.add(new Libro("El corazón de la piedra", 560, "José María García López"));
        libros.add(new Libro("Salmos de vísperas", 95, "Esteban Hernández Castelló"));
        libros.add(new Libro("La música en las catedrales españolas del Siglo de Oro", 600, "Robert Stevenson"));
        libros.add(new Libro("Luces de bohemia", 296, "Ramón del Valle-Inclán"));
        libros.add(new Libro("Contando atardeceres", 528, "La vecina rubia"));
        libros.add(new Libro("Master - Roger Federer", 456, "Christopher Clarey"));
        libros.add(new Libro("La teoría de los archipiélagos", 300, "Alice Kellen"));
        libros.add(new Libro("Esperando al diluvio", 576, "Dolores Redondo"));
        libros.add(new Libro("El italiano", 400, "Arturo Pérez Reverte"));
        libros.add(new Libro("Línea de fuego", 688, "Arturo Pérez Reverte"));

        // Obtener la cantidad de libros con más de 500 páginas
        System.out.println("Ejercicio 1");
        long cont1 = libros.stream().filter(l -> l.getNumPaginas() > 500).count();
        System.out.println("Hay " +  cont1 + " libros con más de 500 páginas");

        // Obtener la cantidad de libros con menos de 300 páginas
        System.out.println("\nEjercicio 2");
        long cont2 = libros.stream().filter(l -> l.getNumPaginas() < 300).count();
        System.out.println("Hay " +  cont2 + " libros con menos de 300 páginas");

        // Listar el título de todos aquellos libros con más de 500 páginas
        System.out.println("\nEjercicio 3");
        libros.stream().filter(l -> l.getNumPaginas() > 500).forEach(l -> System.out.println(l.getTitulo()));

        // Mostrar en consola el título de los 3 libros con mayor número de páginas
        System.out.println("\nEjercicio 4");
        libros.stream()
                // .sorted((l1, l2) -> Integer.compare(l2.getNumPaginas(), l1.getNumPaginas()))
                .sorted(Comparator.comparingInt(Libro::getNumPaginas).reversed())
                .limit(3)
                .forEach(l -> System.out.println(l.getTitulo()));

        // Mostrar en consola la suma total de las páginas de todos los libros
        System.out.println("\nEjercicio 5");
        int totalpaginas = libros.stream().mapToInt(Libro::getNumPaginas).reduce(0, Integer::sum);
        System.out.println("El total de páginas es de " + totalpaginas);

        // Mostrar en consola todos aquellos libros que superen el promedio en cuanto a número de páginas se refiere
        System.out.println("\nEjercicio 6");
        double media = libros.stream().mapToInt(Libro::getNumPaginas).average().getAsDouble();
        libros.stream()
                .filter(l -> l.getNumPaginas() > media)
                //.filter(l -> l.getNumPaginas() > (libros.stream().mapToInt(l2 -> l2.getNumPaginas()).average().getAsDouble()))
                .forEach(l -> System.out.println(l.getTitulo()));

        // Mostrar en consola los autores de todos los libros, sin repetir nombres de autores
        System.out.println("\nEjercicio 7");
        libros.stream().map(Libro::getAutor).distinct().forEach(System.out::println);

        // Mostrar en consola los autores que tengan más de 1 libro listado
        System.out.println("\nEjercicio 8");
        // Agrupamos en una estructura mapa (clave -> valor) los autores con su frecuencia
        libros.stream().collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()))
                // Convertimos la estructura mapa en un stream
                .entrySet().stream()
                // Nos quedamos con los autores cuya frecuencia sea mayor que 1
                .filter(m -> m.getValue() > 1)
                // Extraemos los nombres de los autores y los imprimimos
                .map(Map.Entry::getKey).forEach(System.out::println);

        // Obtener el libro con mayor número de páginas
        System.out.println("\nEjercicio 9");
        System.out.println(libros.stream().max(Comparator.comparingInt(Libro::getNumPaginas)).get().getTitulo());

        // Crear una lista de Strings con todos los títulos de los libros
        System.out.println("\nEjercicio 10");
        List<String> titulos = libros.stream().map(Libro::getTitulo).toList();
        System.out.println(titulos);
    }
}
