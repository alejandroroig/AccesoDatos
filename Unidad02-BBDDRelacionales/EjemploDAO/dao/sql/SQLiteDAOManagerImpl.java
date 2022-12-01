package dao.sql;

import dao.DAOManager;
import dao.EscuderiaDAO;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDAOManagerImpl implements DAOManager {
    final String RUTABASE = Path.of("Unidad02-BBDDRelacionales","EjemploDAO", "db", "formula1.db").toString();

    private Connection conexion;

    private EscuderiaDAO escuderias = null;
    // private PilotoDAO pilotos = null;
    // ...

    public SQLiteDAOManagerImpl() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + RUTABASE);
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public EscuderiaDAO getEscuderiaDAO() {
        if (escuderias == null) {
            escuderias = new SQLiteEscuderiaDAOImpl(conexion);
        }
        return escuderias;
    }
}
