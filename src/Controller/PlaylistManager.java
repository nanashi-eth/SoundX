package Controller;


import Exceptions.MyException;
import Modelo.Cancion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistManager extends ResultSetManager implements Queryable{

    private PreparedStatement st = null;
    private ResultSet rs = null;

    public PlaylistManager() {
    }

    public List<Cancion> getCancionesFromPlaylist(int playlistID) throws MyException {
        List<Cancion> canciones = new ArrayList<>();
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(GET_ALL_SONGS_FROM_PLAYLIST);
            st.setInt(1, playlistID);
            rs = st.executeQuery();
            // Mover al primer resultado del ResultSet
            CancionManager cancionManager = new CancionManager();
            while (rs.next()) {
                Cancion cancion = new Cancion(
                        rs.getString("nombreCancion"),
                        rs.getDate("fecha"),
                        cancionManager.obtenerNombreAutor(rs.getInt("autorID")),
                        rs.getString("imagen"),
                        rs.getFloat("duracion")
                );
                cancion.setCancionID(rs.getInt("cancionID"));
                canciones.add(cancion);
                // Mover al siguiente resultado del ResultSet
                moveResultSetCursor(rs, ResultSetMovement.NEXT);
            }
        } catch (SQLException ex) {
            handleSQLException(209);
        } finally {
            // Cerrar ResultSet y PreparedStatement utilizando el método de la clase padre
            closeResultSetAndStatement(rs, st);
        }
        return canciones;
    }

    public void insertCancionIntoPlaylist(int playlistID, int cancionID) throws MyException {
        try {
            ConnectionDB.openConnection();
            PreparedStatement insertStmt = ConnectionDB.getConnection().prepareStatement(Queryable.INSERT_SONG_INTO_PLAYLIST);
            insertStmt.setInt(1, playlistID);
            insertStmt.setInt(2, cancionID);
            insertStmt.executeUpdate();
            updatePlaylistDuration(playlistID);
        } catch (SQLException ex) {
            handleSQLException(210);
        }
    }

    public void deleteCancionFromPlaylist(int playlistID, int cancionID) throws MyException {
        try {
            ConnectionDB.openConnection();
            PreparedStatement deleteStmt = ConnectionDB.getConnection().prepareStatement(Queryable.DELETE_SONG_FROM_PLAYLIST);
            deleteStmt.setInt(1, playlistID);
            deleteStmt.setInt(2, cancionID);
            deleteStmt.executeUpdate();
            updatePlaylistDuration(playlistID);
        } catch (SQLException ex) {
            handleSQLException(211);
        }
    }

    private void updatePlaylistDuration(int playlistID) throws MyException {
        try {
            PreparedStatement updateStmt = ConnectionDB.getConnection().prepareStatement(Queryable.UPDATE_PLAYLIST_DURATION);
            updateStmt.setInt(1, playlistID);
            ResultSet playlistResultSet = updateStmt.executeQuery();
            if (playlistResultSet.next()) {
                float minutosTotales = playlistResultSet.getFloat("minutosTotales");
                PreparedStatement updatePlaylistStmt = ConnectionDB.getConnection().prepareStatement(Queryable.UPDATE_PLAYLIST_TOTAL_MINUTES);
                updatePlaylistStmt.setFloat(1, minutosTotales);
                updatePlaylistStmt.setInt(2, playlistID);
                updatePlaylistStmt.executeUpdate();
            }
        } catch (SQLException ex) {
            handleSQLException(212);
        }
    }

    public void clearData() {
        // Cerrar ResultSet y PreparedStatement utilizando el método de la clase padre
        super.closeResultSetAndStatement(rs, st);
    }
}

