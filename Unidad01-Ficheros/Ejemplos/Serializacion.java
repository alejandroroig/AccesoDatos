import java.io.*;
import java.nio.file.Path;

class Empleado implements Serializable
{
    private static final long serialVersionUID = 100L;
    String nombre;
    int edad;
    double sueldo;

    Empleado(String empNombre, int empEdad, double empSueldo)
    {
        this.nombre = empNombre;
        this.edad = empEdad;
        this.sueldo = empSueldo;
    }

    public void escribir() {
        System.out.println("Empleado -> nombre: " + nombre + ", edad: " + edad + ", sueldo: " +  sueldo);
    }
}

public class Serializacion {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejemplos","empleados.dat");

        // En el caso de querer serializar una lista de objetos, lo más intuitivo
        // es recorrer la colección y serializar uno a uno los objetos que la contienen
        // Sin embargo, tenemos la opción de serializar colecciones enteras siempre
        // que los elementos contenidos sean serializables.
        //
        // Por ejemplo:
        // List<Empleado> empleados = new ArrayList<>();
        // empleados.add(new Empleado(...));
        // empleados.add(new Empleado(...));
        // oos.writeObject(empleados);
        // List<Empleado> empleados2 = (List<Empleado>)(ois.readObject());
        try (FileOutputStream fos = new FileOutputStream(nombreFichero.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            Empleado emp = new Empleado("Juan Palomo", 25, 1400);
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
