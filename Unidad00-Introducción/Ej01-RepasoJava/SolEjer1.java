import java.util.Scanner;

public class SolEjer1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dime tu nombre: ");
        String nombre = sc.nextLine();
        for (int i = 1; i<=5; i++)
            System.out.println("Hola " + nombre);
    }
}
