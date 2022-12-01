package dao.sql;

import dao.EscuderiaDAO;
import models.Escuderia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteEscuderiaDAOImpl implements EscuderiaDAO {

    final String FINDALL = "SELECT * FROM Teams";
    final String FINDBYID = "SELECT * FROM Teams WHERE ConstructorID = ?";
    final String SAVE = "INSERT INTO Teams (ConstructorID, Chassis, PowerUnit) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE Teams SET Chassis = ?, PowerUnit = ? WHERE ConstructorID = ?";
    final String DELETE = "DELETE FROM Teams WHERE ConstructorID = ?";

   private Connection conexion = null;

    public SQLiteEscuderiaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Escuderia> findAll() {
        List<Escuderia> escuderias = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL);
             ResultSet rs = sentencia.executeQuery()){
            while (rs.next()) {
                escuderias.add(convertToEscuderia(rs));
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return escuderias;
    }

    @Override
    public Escuderia findById(String id) {
        Escuderia escuderia = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setString(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                escuderia = convertToEscuderia(rs);
            }
            rs.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return escuderia;
    }

    @Override
    public void save(Escuderia escuderia) {
        try (PreparedStatement sentencia = conexion.prepareStatement(SAVE)) {
            sentencia.setString(1, escuderia.getNombre());
            sentencia.setString(2, escuderia.getChasis());
            sentencia.setString(3, escuderia.getMotor());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update(Escuderia escuderia) {
        try (PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setString(1, escuderia.getChasis());
            sentencia.setString(2, escuderia.getMotor());
            sentencia.setString(3, escuderia.getNombre());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void deleteById(String id) {
        try (PreparedStatement sentencia = conexion.prepareStatement(DELETE)) {
            sentencia.setString(1, id);
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private Escuderia convertToEscuderia(ResultSet rs) throws SQLException {
        return new Escuderia(
                rs.getString("constructorid"),
                rs.getString("chassis"),
                rs.getString("powerunit"));
    }
}
