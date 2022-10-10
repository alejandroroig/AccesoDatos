import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AccesoAleatorio {

    public static void mostrarFichero() {
        try (RandomAccessFile raf = new RandomAccessFile("enteros.dat", "r")) {
            raf.seek(0); //nos situamos al principio
            while (true) {
                int n = raf.readInt();  //se lee un entero del fichero
                System.out.println(n);  //se muestra en pantalla
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero;

        /* Pide un número entero por teclado y lo añade al final de un fichero binario enteros.dat que contiene números enteros.
        El programa utiliza un método mostrarFichero() que se llama dos veces. La primera muestra el contenido del fichero antes
        de añadir el nuevo número y la segunda llamada muestra el fichero después de añadirlo.*/
        try (RandomAccessFile raf = new RandomAccessFile("enteros.dat", "rw")) {

            mostrarFichero();//muestra el contenido del fichero después de añadir el número

            System.out.print("Introduce un número entero para añadir al final del fichero: ");
            numero = sc.nextInt(); //se lee el entero a añadir en el fichero
            raf.seek(raf.length()); //nos situamos al final del fichero
            raf.writeInt(numero); //se escribe el entero

            mostrarFichero();//muestra el contenido del fichero después de añadir el número
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /* Modifica un entero dentro del fichero enteros.dat con acceso aleatorio.
        Para ello se pide la posición que ocupa el entero a modificar dentro del fichero,
        a continuación se lee y muestra el valor actual, se pide el nuevo valor y finalmente se escribe el nuevo valor
        en la posición indicada, modificando de esta forma el valor antiguo por el nuevo.*/
        try (RandomAccessFile raf = new RandomAccessFile("enteros.dat", "rw")) {
            //calcular cuántos enteros tiene el fichero
            long size = raf.length();
            size = size / 4;
            System.out.println("El fichero tiene " + size + " enteros");

            //Modificar el entero que se encuentra en una posición determinada
            int pos;
            do {
                System.out.println("Introduce una posición (>=1 y <= " + size + "): ");
                pos = sc.nextInt();
            } while (pos < 1 || pos > size);

            pos--;  //la posición 1 realmente es la 0

            //nos situamos en la posición (byte de inicio) del entero a modificar
            //en Java un entero ocupa 4 bytes
            raf.seek(pos * 4L);

            //leemos y mostramos el valor actual
            System.out.println("Valor actual: " + raf.readInt());

            //pedimos que se introduzca el nuevo valor
            System.out.println("Introduce nuevo valor: ");
            numero = sc.nextInt();

            //nos situamos de nuevo en la posición del entero a modificar
            //esto es necesario porque después de la lectura que hemos realizado para mostrar
            //el valor el puntero de lectura/escritura ha avanzado al siguiente entero del fichero.
            //si no hacemos esto escribiremos sobre el siguiente entero
            raf.seek(pos * 4L);

            //escribimos el entero
            raf.writeInt(numero);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

