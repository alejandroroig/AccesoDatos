import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class ListaDeDatos {
    ArrayList<String> lista;

    public ListaDeDatos() {
        lista = new ArrayList<>();
    }

    public void incluir(String texto) {
        lista.add(texto);
    }

    public boolean contiene(String texto) {
        return lista.contains(texto);
    }

    public void mostrarDatosOrdenados() {
        Collections.sort(lista);
        System.out.println(lista);
    }

    // Otra versión usando stream (la salida no es exactamente igual)
    public void mostrarDatosOrdenadosFunc() {
        lista.stream().forEachOrdered(System.out::println);
    }
}

public class SolEjer8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDeDatos lista = new ListaDeDatos();

        String opcion;

        System.out.println("Fase de petición de datos");
        do {
            System.out.print("Introduce una cadena (fin para salir): ");
            opcion = sc.nextLine();
            if (!opcion.equals("fin")) lista.incluir(opcion);
        } while (!opcion.equals("fin"));


        // Mostrar datos
        System.out.println("Elementos en la lista");
        lista.mostrarDatosOrdenados();
    }
}
