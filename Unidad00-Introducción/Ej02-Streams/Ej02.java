import java.util.ArrayList;
import java.util.List;

class Libro
{
    private String titulo;
    private int numPaginas;
    private String autor;

    public Libro(String titulo, int numPaginas, String autor)
    {
        this.titulo = titulo;
        this.numPaginas = numPaginas;
        this.autor = autor;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getAutor()
    {
        return autor;
    }

    public int getNumPaginas()
    {
        return numPaginas;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNumPaginas(int numPaginas)
    {
        this.numPaginas = numPaginas;
    }
}

public class Ej02
{
    public static void main(String[] args)
    {
        List<Libro> libros = new ArrayList<>();

        libros.add(new Libro("El Señor de los Anillos", 800, "J.R.R. Tolkien"));
        libros.add(new Libro("El Hobbit", 350, "J.R.R. Tolkien"));
        libros.add(new Libro("Cabo Trafalgar", 320, "Arturo Pérez Reverte"));
        libros.add(new Libro("El corazón de la piedra", 560, "José María García López"));
        libros.add(new Libro("Salmos de vísperas", 95, "Esteban Hernández Castelló"));
        libros.add(new Libro("La música en las catedrales españolas del Siglo de Oro", 600, "Robert Stevenson"));
        libros.add(new Libro("Luces de bohemia", 296, "Ramón del Valle-Inclán"));
        libros.add(new Libro("Contando atardecere", 528, "La vecina rubia"));
        libros.add(new Libro("Master - Roger Federer", 456, "Christopher Clarey"));
        libros.add(new Libro("La teoría de los archipiélagos", 300, "Alice Kellen"));
        libros.add(new Libro("Esperando al diluvio", 576, "Dolores Redondo"));
        libros.add(new Libro("El italiano", 400, "Arturo Pérez Reverte"));
        libros.add(new Libro("Línea de fuego", 688, "Arturo Pérez Reverte"));

        for(Libro l: libros)
        {
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor());
        }
    }
}