package Controller;

import Exceptions.MyException;
import Modelo.Cancion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionManager extends ResultSetManager {

    private PreparedStatement st = null;
    private ResultSet rs = null;

    public List<Cancion> getAllCanciones() throws MyException {
        List<Cancion> canciones = new ArrayList<>();
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(Queryable.GET_ALL_SONGS_WITH_AUTHOR);
            rs = st.executeQuery();
            // Mover al primer resultado del ResultSet
            moveResultSetCursor(rs, ResultSetMovement.FIRST);
            while (!rs.isAfterLast()) {
                Cancion cancion = new Cancion(
                        rs.getString("nombreCancion"),
                        rs.getDate("fecha"),
                        rs.getString("nomAutor"),
                        rs.getString("imagen"),
                        rs.getFloat("duracion")
                );
                cancion.setCancionID(rs.getInt("cancionID"));
                canciones.add(cancion);
                // Mover al siguiente resultado del ResultSet
                moveResultSetCursor(rs, ResultSetMovement.NEXT);
            }
        } catch (SQLException ex) {
            handleSQLException(213);
        } finally {
            // Cerrar ResultSet y PreparedStatement utilizando el método de la clase padre
            closeResultSetAndStatement(rs, st);
        }
        return canciones;
    }

    public int countAllCanciones() throws MyException {
        int count = 0;
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(Queryable.COUNT_ALL_SONGS);
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
            st = ConnectionDB.getConnection().prepareStatement(Queryable.COUNT_ALL_SONGS_IN_PLAYLIST);
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



