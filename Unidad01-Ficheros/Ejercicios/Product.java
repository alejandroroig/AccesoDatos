import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private int supplier;
    private int category;
    private double unitPrice;
    private int unitsInStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void writeFile(String nomfich) {
        Path ruta = Path.of(nomfich);

        String coma = ",";
        String producto = this.getId() + coma +
                this.getName() + coma +
                this.getSupplier() + coma +
                this.getCategory() + coma + coma +
                this.getUnitPrice() + coma +
                this.getUnitsInStock() + coma + coma + coma;

        try {
            if (Files.exists(ruta)){
                Files.writeString(ruta, "\n" + producto, APPEND);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeFileEj6(String nomfich) {
        String coma = ",";
        String producto = this.getId() + coma +
                this.getName() + coma +
                this.getSupplier() + coma +
                this.getCategory() + coma + coma +
                this.getUnitPrice() + coma +
                this.getUnitsInStock() + coma + coma + coma;

        try (RandomAccessFile raf = new RandomAccessFile(nomfich, "rws")) {
            String nombre = this.getName();
            String linea;
            long pos = 0;
            boolean encontrado = false;

            linea = raf.readLine(); // leemos la primera l??nea
            if (linea != null) linea = raf.readLine(); // y si no es final de fichero, la primera se descarta

            while (linea != null && !encontrado) { // mientras no lleguemos a final de fichero y no se haya encontrado
                String[] partesProducto = linea.split(","); // separamos los campos de la l??nea
                if (partesProducto[1].equals(nombre)) { // si el nombre del producto coincide con el le??do
                    raf.seek(pos); // nos posicionamos al principio de la l??nea actual
                    raf.writeBytes(producto); // sobreescribimos la l??nea completa
                    encontrado = true; // no se requiere buscar m??s
                }
                pos = raf.getFilePointer(); // posici??n de la l??nea a leer
                linea = raf.readLine(); // lectura de la nueva l??nea
            }

            if (!encontrado) {
                raf.seek(raf.length());
                raf.writeBytes(producto);
                raf.writeBytes("\n");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier=" + supplier +
                ", category=" + category +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                '}';
    }


    @Override
    public int compareTo(Product p) {
        if (this.getUnitsInStock() < p.getUnitsInStock())
            return -1;
        else if (this.getUnitsInStock() > p.getUnitsInStock())
            return 1;
        else
            return 0;
    }
}