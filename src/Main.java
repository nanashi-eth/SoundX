import Controller.ConnectionDB;
import Exceptions.MyException;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            // Abre la conexión a la base de datos
            ConnectionDB.openConnection();

            // Ejecuta el script SQL desde el archivo especificado
            ConnectionDB.executeScript();

            ConnectionDB.openConnection();
            // Realiza una consulta de ejemplo para verificar que el script se haya ejecutado correctamente
            try (Connection conn = ConnectionDB.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM CANCION")) {

                // Itera sobre los resultados y haz algo con ellos
                while (rs.next()) {
                    // Supongamos que tienes columnas 'id', 'nombre' y 'artista' en tu tabla CANCION
                    int id = rs.getInt("cancionID");
                    String nombre = rs.getString("nombreCancion");
                    int artista = rs.getInt("autorID");

                    // Hacer algo con los datos (por ejemplo, imprimirlos)
                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Artista: " + artista);
                }
            }

        } catch (MyException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cierra la conexión a la base de datos
                ConnectionDB.cerrarConexion();
            } catch (MyException e) {
                e.printStackTrace();
                // Manejar errores aquí
            }
        }
    }
}
