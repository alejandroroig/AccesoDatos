import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SolEj03 {

    public static Product crearProducto(String lineaProducto) {
        String[] partesProducto = lineaProducto.split(",");
        Product producto = new Product();

        producto.setId(Integer.parseInt(partesProducto[0]));
        producto.setName(partesProducto[1]);
        producto.setSupplier(Integer.parseInt(partesProducto[2]));
        producto.setCategory(Integer.parseInt(partesProducto[3]));
        // partesProducto[4] no se almacena
        producto.setUnitPrice(Double.parseDouble(partesProducto[5]));
        producto.setUnitsInStock(Integer.parseInt(partesProducto[6]));

        return producto;
    }

    // Forma funcional de conseguir la lista de productos
    public static List<Product> listaProductosFunc() throws IOException {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejercicios","Ej03-LeerFichero.csv");

        // map(SolEj03::crearProducto) == map(lp -> crearProducto(lp))
        return Files.lines(nombreFichero).skip(1).map(SolEj03::crearProducto).toList();
    }

    // Forma imperativa de conseguir la lista de productos
    public static List<Product> listaProductosImp() throws IOException {
        Path nombreFichero = Path.of("Unidad01-Ficheros\\Ejercicios","Ej03-LeerFichero.csv");

        List<String> listaProductos = Files.readAllLines(nombreFichero);
        listaProductos.remove(0); // La primera línea es solo descriptiva

        List<Product> productos = new ArrayList<>();

        for (String lineaProducto: listaProductos) {
            productos.add(crearProducto(lineaProducto));
        }
        return productos;
    }

    public static void main(String[] args) {
        List<Product> productos = null;

        try {
            productos = listaProductosFunc();
            // productos = listaProductosImp();

        } catch (Exception e) {
            System.err.println("Error de lectura del fichero");
        }

        assert productos != null;

        // Imprimir la lista de productos
        System.out.println("\nLista de productos:");
        productos.forEach(System.out::println);

        // Imprimir los nombres de los productos
        System.out.println("\nNombres de los productos:");
        productos.stream().map(Product::getName).forEach(System.out::println);

        // Imprimir los nombres de los productos cuyas unidades en stock sean menor que 10
        System.out.println("\nNombres de los productos con stock bajo:");
        productos.stream().filter(p -> p.getUnitsInStock() < 10).map(Product::getName).forEach(System.out::println);

        // Imprimir el nombre de los productos con unidades en stock mayor de 10 ordenados
        // por unidad de stock de forma descendente
        System.out.println("\nNombres de los productos con stock alto ordenado descendente:");
        productos.stream()
                .filter(p -> p.getUnitsInStock() > 10)
                .sorted(Comparator.reverseOrder())
                .map(Product::getName)
                .forEach(System.out::println);

        // Imprimir el número de productos agrupados por proveedor
        System.out.println("\nNombres de los proveedores y sus productos:");
        productos.stream()
                .collect(Collectors.groupingBy(Product::getSupplier, Collectors.counting()))
                .forEach((p, c) -> System.out.printf("Proveedor: %s; Nº productos: %s \n", p, c));

        // Imprimir el producto con el precio unitario más alto
        System.out.println("\nProducto con el precio unitario más alto:");
        System.out.println(productos.stream().max(Comparator.comparing(Product::getUnitPrice)).get());

        // Imprimir el promedio de existencias en almacén
        System.out.println("\nPromedio de existencias en almacén:");
        System.out.format("%.2f \n", productos.stream().mapToInt(Product::getUnitsInStock).average().getAsDouble());

    }
}
