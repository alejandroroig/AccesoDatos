import java.nio.file.Path;
import java.sql.*;

public class Mundial {
    public static void main(String[] args) {
        // Conexión a la base
        Connection con = null;

        // PreparedStatement para consultas
        PreparedStatement statement = null;

        try {

            // Abrimos la conexión con la base de datos
            // Para ello cargaremos el driver escogido:
            Class.forName("org.sqlite.JDBC");
            // Y establecemos una conexión, indicando la URL de la BBDD (en otros casos, URL del servidor, usuario y contraseña)
            Path databasePath = Path.of("Unidad02-BBDDRelacionales","EjemploJDBCySQLITE", "db", "formula1.db");
            con = DriverManager.getConnection("jdbc:sqlite:" + databasePath.toString());

            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String sentenciaSQL = "SELECT DriverID, Name, strftime('%d/%m/%Y', DateOfBirth) AS dob, Team FROM Drivers ORDER BY Name";
            statement = con.prepareStatement(sentenciaSQL);
            // La orden SQL se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
            // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
            ResultSet rs = statement.executeQuery();

            // Consumimos los resultados de la consulta
            System.out.println("No\tNombre\t\t\tFecha Nac.\tEquipo");
            System.out.println("----------------------------------------------");
            // El conjunto de resultados se recorre de forma secuencial: rs.next() será verdadero si hay más datos en el set.
            while (rs.next()) {
                // Si cada fila del resultado está formada por varios campos, podemos obtener el valor de cada uno de ellos con rs.getString(x),
                // donde x puede ser la posición de la columna (empezando con 1) o el nombre del campo (indiferente de mayúsculas o minúsculas
                // System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
                System.out.println(rs.getInt("driverid") + "\t" + rs.getString("name") +
                        "\t" + rs.getString("dob") + "\t" + rs.getString("team"));
            }

            // Finalmente, se debe cerrar el ResultSet y la conexión
            rs.close();
            con.close();

        } catch ( Exception e ) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
