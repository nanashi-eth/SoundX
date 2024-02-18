package Controller;

import Exceptions.MyException;
import Modelo.Cancion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionManager extends ResultSetManager implements Queryable{

    private PreparedStatement st = null;
    private ResultSet rs = null;
    public CancionManager() {
        // Constructor vacío
    }

    public List<Cancion> getAllCanciones() throws MyException {
        List<Cancion> canciones = new ArrayList<>();
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(GET_ALL_SONGS_WITH_AUTHOR);
            rs = st.executeQuery();
            // Mover al primer resultado del ResultSet
            while (!rs.next()) {
                Cancion cancion = new Cancion(
                        rs.getString("C.nombreCancion"),
                        rs.getDate("C.fecha"),
                        rs.getString("A.nomAutor"),
                        rs.getString("C.imagen"),
                        rs.getFloat("C.duracion")
                );
                cancion.setCancionID(rs.getInt("C.cancionID"));
                canciones.add(cancion);
            }
        } catch (SQLException ex) {
            handleSQLException(213);
        } finally {
            // Cerrar ResultSet y PreparedStatement utilizando el método de la clase padre
            closeResultSetAndStatement(rs, st);
        }
        return canciones;
    }

    public String obtenerNombreAutor(int autorID) throws MyException {
        String nombreAutor = "";
        try {
            PreparedStatement st = ConnectionDB.getConnection().prepareStatement(GET_AUTOR_NAME);
            st.setInt(1, autorID);
            rs = st.executeQuery();
            if (rs.next()) {
                nombreAutor = rs.getString("nomAutor");
            }
        } catch (SQLException ex) {
            handleSQLException(210); 
        }
        finally {
            st = null;
            rs = null;
        }
        return nombreAutor;
    }

    public int countAllCanciones() throws MyException {
        int count = 0;
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(COUNT_ALL_SONGS);
            rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            handleSQLException(214);
        } finally {
            // Cerrar ResultSet y PreparedStatement utilizando el método de la clase padre
            closeResultSetAndStatement(rs, st);
        }
        return count;
    }

    public int countCancionesInPlaylist(int playlistID) throws MyException {
        int count = 0;
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(COUNT_ALL_SONGS_IN_PLAYLIST);
            st.setInt(1, playlistID);
            rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            handleSQLException(215);
        } finally {
            // Cerrar ResultSet y PreparedStatement utilizando el método de la clase padre
            closeResultSetAndStatement(rs, st);
        }
        return count;
    }

    public void clearData() { 
        // Cerrar ResultSet y PreparedStatement utilizando el método de la clase padre
        super.closeResultSetAndStatement(rs, st);
    }
}



