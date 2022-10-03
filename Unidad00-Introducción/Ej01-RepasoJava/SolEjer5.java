import java.util.Scanner;

public class SolEjer5 {
    public static boolean esPrimo(int numero) {
        if (numero <= 1 || numero % 2 == 0) return false;
        for (int i=3; i*i <= numero; i+=2) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    public static boolean esPalindromo(int numero) {
        int aux = numero;
        int revertido = 0;
        do {
            revertido = (revertido * 10) + (aux % 10);
            aux = aux / 10;

        } while (aux > 0);

        return numero == revertido;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dame un entero: ");
        int numero = sc.nextInt();

        if (esPrimo(numero) && esPalindromo(numero)) {
            System.out.println("Es número primo y palíndromo");
        }
        else
            System.out.println("No es número primo y palíndromo");
    }
}
