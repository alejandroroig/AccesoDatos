import java.util.ArrayList;
import java.util.Scanner;

public class SolEjer6 {
    public static void main(String[] args) {

        //Fase de petición de datos
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> listaEnteros = new ArrayList<>();
        int opcion = 1;

        System.out.println("Fase de petición de datos");
        do {
            System.out.print("Introduce un número entero positivo (negativo para salir): ");
            opcion = sc.nextInt();
            if (opcion >= 0) listaEnteros.add(opcion);
        } while (opcion >= 0);


        // Fase de búsqueda de datos
        opcion = 1;
        System.out.println("Fase de búsqueda de datos");
        do {
            System.out.print("Introduce un número entero positivo (negativo para salir): ");
            opcion = sc.nextInt();
            if (listaEnteros.contains(opcion))
                System.out.println("El elemento " + opcion +  " está en la lista");
            else
                System.out.println("El elemento " + opcion +  " NO está en la lista");
        } while (opcion >= 0);
    }
}
