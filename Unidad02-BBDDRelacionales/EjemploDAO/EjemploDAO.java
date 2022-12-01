import dao.sql.SQLiteDAOManagerImpl;
import models.Escuderia;
import dao.sql.SQLiteEscuderiaDAOImpl;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

public class EjemploDAO {
    public static void main(String[] args) {

            SQLiteDAOManagerImpl mundial = new SQLiteDAOManagerImpl();

            System.out.println("\nMostrar todas");
            System.out.println("-------------------------------");
            mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

            System.out.println("\nMostrar solo una");
            System.out.println("-------------------------------");
            System.out.println(mundial.getEscuderiaDAO().findById("Mercedes"));

            System.out.println("\nInsertar una escudería");
            System.out.println("-------------------------------");
            Escuderia seat = new Escuderia("Seat", "Seat-2019", "Seat 4 Latas");
            mundial.getEscuderiaDAO().save(seat);
            mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

            System.out.println("\nActualizar una escudería");
            System.out.println("-------------------------------");
            seat.setChasis("Seat-2023");
            seat.setMotor("Seat TDI 2.0");
            mundial.getEscuderiaDAO().update(seat);
            mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

            System.out.println("\nBorrar una escudería");
            System.out.println("-------------------------------");
            mundial.getEscuderiaDAO().deleteById("Seat");
            mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

            mundial.close();
    }
}
