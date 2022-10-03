import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class LeerEscribirBytes {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","ficheroBytes");

        byte[] bytes = "Ejemplo de texto para fichero binario".getBytes();

        // Opción 1: Escritura con Files.write() de java.nio y lectura con Files.readAllBytes
        System.out.println("\nLectura y escritura binaria con métodos de Files");
        try {
            // Escritura
            Files.write(nombreFichero, bytes, CREATE);
            // Lectura
            byte[] leidos = Files.readAllBytes(nombreFichero);
            System.out.println(new String(leidos));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Opción 2: FileOutputStream y FileInputStream
        System.out.println("\nLectura y escritura binaria con FileInputStream y FileOutputStream");
        try (FileOutputStream fos = new FileOutputStream(nombreFichero.toFile());
             FileInputStream fis = new FileInputStream(nombreFichero.toFile())) {
            // Escritura
            fos.write(bytes);

            // Lectura
            int c;
            while((c = fis.read()) != -1){
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Lectura y escritura de tipos de datos primitivos
        System.out.println("\nLectura y escritura de datos primitivos");
        Path ficheroLibros = Path.of("Unidad01-Ficheros\\Ejemplos","ficheroLibroBytes");
        String[] titulos = {
                "Libro1",
                "Libro2",
                "Libro3",
                "Libro4",
                "Libro5"
        };
        double[] precios = { 19.99, 9.99, 15.99, 23.99, 34.99 };
        int[] unidades = { 12, 8, 13, 29, 50 };

        // Escritura
        try(FileOutputStream fos = new FileOutputStream(ficheroLibros.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos)) {
            for (int i = 0; i < titulos.length; i ++) {
                dos.writeUTF(titulos[i]);
                dos.writeDouble(precios[i]);
                dos.writeInt(unidades[i]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Lectura
        try(FileInputStream fis = new FileInputStream(ficheroLibros.toFile());
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis)) {

            double precio;
            int cantidad;
            String titulo;

            while (dis.available() > 0) {
                titulo = dis.readUTF();
                precio = dis.readDouble();
                cantidad = dis.readInt();
                System.out.format("Has pedido %d" + " unidades de %s, que cuestan %.2f €%n", cantidad, titulo, precio);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
