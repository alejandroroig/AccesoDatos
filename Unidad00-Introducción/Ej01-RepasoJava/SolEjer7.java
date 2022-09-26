import java.util.ArrayList;
import java.util.Scanner;

public class SolEjer7 {
    public static void main(String[] args) {
        //Fase de petición de datos
        Scanner sc = new Scanner(System.in);
        ArrayList<String> listaCadenas = new ArrayList<>();
        String opcion;

        System.out.println("Fase de petición de datos");
        do {
            System.out.print("Introduce una cadena (fin para salir): ");
            opcion = sc.nextLine();
            if (!opcion.equals("fin")) listaCadenas.add(opcion);
        } while (!opcion.equals("fin"));


        // Fase de búsqueda de datos
        System.out.println("Fase de búsqueda de datos");
        do {
            System.out.print("Introduce una cadena (fin para salir): ");
            opcion = sc.nextLine();
            if (listaCadenas.contains(opcion))
                System.out.println("El elemento " + opcion +  " está en la lista");
            else if (!opcion.equals("fin"))
                System.out.println("El elemento " + opcion +  " NO está en la lista");
        } while (!opcion.equals("fin"));
    }
}
