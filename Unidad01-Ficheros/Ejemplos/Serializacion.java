import java.io.*;
import java.nio.file.Path;

class Empleado implements Serializable
{
    int id;
    String nombre;

    Empleado(int empId, String empNombre)
    {
        this.id = empId;
        this.nombre = empNombre;
    }
    public void escribir() {
        System.out.println("Empleado id: " + id + ", nombre: " + nombre);
    }
}

public class Serializacion {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","empleados.dat");
        try (FileOutputStream fos = new FileOutputStream(nombreFichero.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            Empleado emp = new Empleado(1, "Juan Palomo");
            oos.writeObject(emp);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(nombreFichero.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Empleado emp = (Empleado) ois.readObject();
            emp.escribir();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
