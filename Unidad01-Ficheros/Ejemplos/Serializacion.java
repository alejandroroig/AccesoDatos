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
