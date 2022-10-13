import java.nio.file.Path;
import java.util.List;

public class SolEj06 {

    public static void main(String[] args) {

        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejercicios","Ej03-LeerFichero.csv");

        Product producto1 = new Product();
        producto1.setId(100);
        producto1.setName("Tuercas podridas");
        producto1.setSupplier(200);
        producto1.setCategory(300);
        producto1.setUnitPrice(20.50);
        producto1.setUnitsInStock(100);
        producto1.writeFileEj6(nombreFichero.toString());

        Product producto2 = new Product();
        producto2.setId(120);
        producto2.setName("Destornillador sucio");
        producto2.setSupplier(200);
        producto2.setCategory(330);
        producto2.setUnitPrice(22.50);
        producto2.setUnitsInStock(110);
        producto2.writeFileEj6(nombreFichero.toString());

        Product producto3 = new Product();
        producto3.setId(100);
        producto3.setName("Tuercas podridas");
        producto3.setSupplier(100);
        producto3.setCategory(300);
        producto3.setUnitPrice(50.20);
        producto3.setUnitsInStock(10);
        producto3.writeFileEj6(nombreFichero.toString());

        Product producto4 = new Product();
        producto4.setId(200);
        producto4.setName("Navaja oxidada");
        producto4.setSupplier(100);
        producto4.setCategory(300);
        producto4.setUnitPrice(10.20);
        producto4.setUnitsInStock(20);
        producto4.writeFileEj6(nombreFichero.toString());

        Product producto5 = new Product();
        producto5.setId(210);
        producto5.setName("Pizza margarita");
        producto5.setSupplier(100);
        producto5.setCategory(300);
        producto5.setUnitPrice(12.20);
        producto5.setUnitsInStock(20);
        producto5.writeFileEj6(nombreFichero.toString());

        Product producto6 = new Product();
        producto6.setId(100);
        producto6.setName("Tuercas podridas");
        producto6.setSupplier(200);
        producto6.setCategory(300);
        producto6.setUnitPrice(0.50);
        producto6.setUnitsInStock(20);
        producto6.writeFileEj6(nombreFichero.toString());

        try {
            List<Product> productos = SolEj03.listaProductosFunc();
            System.out.println("\nLista de productos:");
            productos.forEach(System.out::println);
        } catch  (Exception e) {
            e.printStackTrace();
        }
    }
}
