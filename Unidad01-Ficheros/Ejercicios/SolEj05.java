import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SolEj05 {
    public static class Persona implements Serializable {

        @Serial
        private static final long serialVersionUID = 100L;
        private String nombre;
        private String email;
        private int anyo;

        public Persona(String nombre, String email, int anyo) {
            this.nombre = nombre;
            this.email = email;
            this.anyo = anyo;
        }

        public String getNombre() { return nombre; }
        public String getEmail() { return email; }
        public int getAnyo() { return anyo; }

        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setEmail(String email) { this.email = email; }
        public void setAnyo(int anyo) { this.anyo = anyo; }

        public String toString() {
            return "Persona -> {nombre: " + nombre + ", email: " + email + ", año de nacimiento: " + anyo + "}";
        }
    }
    public static void main(String[] args) throws IOException {

        // Ejercicio 5.1: Crear tres objetos persona y los guarde en un fichero
        // llamado personas.dat

        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejercicios","personas.dat");
        ArrayList<Persona> agendaEscribir = new ArrayList<>();
        try (FileOutputStream fos = new FileOutputStream(nombreFichero.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            agendaEscribir.add(new Persona("Alejandro Roig", "alejandro.roig@iesalvarofalomir.org", 1987));
            agendaEscribir.add(new Persona("Una Persona", "unapersona@gmail.com", 1950));
            agendaEscribir.add(new Persona("Otra Persona", "otrapersona@gmail.com", 2000));

            oos.writeObject(agendaEscribir);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        // Ejercicio 5.2: Mostrar los datos e las personas guardadas en el
        // fichero personas.dat

        ArrayList<Persona> agendaLeer = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(nombreFichero.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            agendaLeer = (ArrayList<Persona>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Object persona : agendaLeer)
            System.out.println(persona.toString());

        // Ejercicio 5.3: Agenda

        // Leemos contenido de la agenda
        Path agendaFichero = Path.of("Unidad01-Ficheros\\Ejercicios","agenda.dat");
        if (!Files.exists(agendaFichero)) {
            Files.createFile(agendaFichero);
        }

        HashMap<String, Persona> agenda = new HashMap<>();

        if (Files.size(agendaFichero) > 0)
            try (FileInputStream fis = new FileInputStream(agendaFichero.toFile());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                agenda = (HashMap<String, Persona>) ois.readObject();
            } catch (EOFException ignored) {
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        do {
            System.out.println("""
                    1. Introducir persona en agenda
                    2. Buscar persona por nombre
                    3. Salir
                    """);

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduce datos de persona...");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();
                    System.out.println("Año de nacimiento: ");
                    int anyo = sc.nextInt();
                    sc.nextLine();

                    // Si no existe lo crea, si existe lo actualiza
                    agenda.put(nombre, new Persona(nombre, email, anyo));
                }
                case 2 -> {
                    System.out.println("Introduce datos de persona a buscar...");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    if (agenda.containsKey(nombre))
                        System.out.println(agenda.get(nombre).toString());
                    else System.out.println("No existe nadie con ese nombre en la agenda");
                }
                case 3 -> {
                    salir = true;
                    if (!agenda.isEmpty())
                        try (FileOutputStream fos = new FileOutputStream(agendaFichero.toFile());
                             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                            oos.writeObject(agenda);
                        } catch (IOException  e) {
                            e.printStackTrace();
                        }
                }
                default -> System.out.println("Opción invalida");
            }
        } while (!salir);
    }
}
