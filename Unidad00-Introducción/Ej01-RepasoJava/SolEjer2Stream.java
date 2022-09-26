import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

public class SolEjer2Stream {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dame un número entero: ");
        int numero = sc.nextInt();
        System.out.print(numero + " = ");

        ArrayList<Integer> lista = new ArrayList<>();

        for (int i = 2; sqrt(numero) >= i; i++) {
            while (numero%i == 0) {
                numero = numero / i;
                lista.add(i);
            }
        }
        if (numero > 2)
            lista.add(numero);

        System.out.println(lista.stream().map(Object::toString).collect(Collectors.joining(" * ")));
    }
}
