import java.util.Scanner;

public class SolEjer4 {
    public static void main(String[] args) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Scanner sc = new Scanner(System.in);
        System.out.print("Dame un número del 1 al 12: ");
        int mes = sc.nextInt();

        System.out.println("El mes " + mes + " es " +  meses[mes-1]);
    }
}
