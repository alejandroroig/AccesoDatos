import java.util.Scanner;

import static java.lang.Math.sqrt;

public class SolEjer2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dame un número entero: ");
        int numero = sc.nextInt();
        System.out.print(numero + " = ");

        // for (int 1 = 2; numero > i; i++) funcionaría también,
        // pero es más eficiente buscar hasta la raíz cuadrada de numero
        for (int i = 2; sqrt(numero) >= i; i++) {
            while ((numero%i == 0) && (numero != i)) {
                System.out.print(i + " * ");
                numero = numero / i;
            }
        }
        System.out.println(numero);

    }
}
