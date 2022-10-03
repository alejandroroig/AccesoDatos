import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SolEjer2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dame un número entero: ");
        int numero = sc.nextInt();
        int aux = numero;
        List<Integer> factoresPrimos = new ArrayList<>();

        // for (int 1 = 2; numero > i; i++) funcionaría también,
        // pero es más eficiente buscar hasta la raíz cuadrada de numero
        for (int i = 2; i * i <= aux; i++) {
            while ((aux%i == 0) && (aux != i)) {
                factoresPrimos.add(i);
                aux /= i;
            }
        }
        if (aux > 2)
            factoresPrimos.add(aux);

        System.out.println(numero + " = " + factoresPrimos.stream().map(Objects::toString).collect(Collectors.joining(" * ")));

    }
}
