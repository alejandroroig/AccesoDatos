import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UsoPath {
    public static void main(String[] args) throws IOException {
        // Path
        System.out.println("# info");
        Path relative = Paths.get(".");
        Path absolute = relative.toAbsolutePath().normalize();

        System.out.printf("Relative: %s%n", relative);
        System.out.printf("Absolute: %s%n", absolute);
        System.out.printf("Name count: %d%n", absolute.getNameCount());
        System.out.printf("Parent: %s%n", absolute.getParent());
        System.out.printf("Subpath(0, 2): %s%n", absolute.subpath(0, 2));

        // File operations
        Path file = Paths.get("prueba.iml");
        Path backup = Paths.get("prueba.iml.backup");
        Path rename = Paths.get("prueba.iml.backup.1");
        Files.copy(file, backup, StandardCopyOption.REPLACE_EXISTING);
        Files.move(backup, rename, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(rename);

        // Copiar ficheros y directorios JAVA NIO2
        //Files.copy(path, out)


        // borrar ficheros o directorios
        //fichero.delete();
        //directorio.delete();
        //Files.deleteIfExists(path)

        // Enlaces y otras operaciones en la clase Files
        // Files.createLink(path, path1)
        // Files.size();
        // Files.move();

        // Read leemos el contenido de un fichero
        System.out.println("");
        System.out.println("# prueba.iml");
        Files.readAllLines(file).forEach(System.out::println);
    }
}
