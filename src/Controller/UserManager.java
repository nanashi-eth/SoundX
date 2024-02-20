package Controller;

import Exceptions.MyException;
import Modelo.Playlist;
import Modelo.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager extends ResultSetManager implements Queryable {
    

    private PreparedStatement st = null;
    private ResultSet rs = null;

    public UserManager() throws MyException {
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(GET_ALL_USERS, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            handleSQLException(202);
        }
    }

    public UserManager(String username) throws MyException {
        try {
            ConnectionDB.openConnection();
            st = ConnectionDB.getConnection().prepareStatement(GET_USER_BY_NAME, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setString(1, username);
            rs = st.executeQuery();
            // Mover al primer resultado del ResultSet
            moveResultSetCursor(rs, ResultSetMovement.FIRST);
        } catch (SQLException ex) {
            handleSQLException(202);
        }
    }

    public int getUserIdByName(String username) throws MyException {
        int userId = -1; // Valor por defecto si no se encuentra el usuario
        try {
            PreparedStatement getUserIdStmt = ConnectionDB.getConnection().prepareStatement(GET_USER_ID_BY_NAME);
            getUserIdStmt.setString(1, username);
            ResultSet userIdResultSet = getUserIdStmt.executeQuery();
            if (userIdResultSet.next()) {
                userId = userIdResultSet.getInt("userID");
            }
        } catch (SQLException ex) {
            handleSQLException(202);
        }
        return userId;
    }
    
    public String getUserImgByName(String username) throws MyException {
        String img = ""; // Valor por defecto si no se encuentra el usuario
        try {
            PreparedStatement getUserIdStmt = ConnectionDB.getConnection().prepareStatement(GET_USER_BY_NAME);
            getUserIdStmt.setString(1, username);
            ResultSet userIdResultSet = getUserIdStmt.executeQuery();
            if (userIdResultSet.next()) {
                img = userIdResultSet.getString("imagen");
            }
        } catch (SQLException ex) {
            handleSQLException(202);
        }
        return img;
    }

    public boolean usernameExists(String username) throws MyException {
        try {
            PreparedStatement usernameExistsStmt = ConnectionDB.getConnection().prepareStatement(USER_EXISTS_BY_NAME);
            usernameExistsStmt.setString(1, username);
            ResultSet usernameExistsResultSet = usernameExistsStmt.executeQuery();
            return usernameExistsResultSet.next();
        } catch (SQLException ex) {
            handleSQLException(202);
            return false;
        }
    }

    public boolean validateUsuario(String username, String password) throws MyException {
        try {
            return rs.getString("nombreUsuario").equals(username) && rs.getString("password").equals(password);
        } catch (SQLException ex) {
            handleSQLException(201);
            return false;
        }
    }

    public void insertUsuario(Usuario user) throws MyException {
        try {
            if (usernameExists(user.getNombreUsuario())) {
                throw new MyException(201);
            }
            rs.moveToInsertRow();
            rs.updateString("nombreUsuario", user.getNombreUsuario());
            rs.updateString("password", user.getPassword());
            rs.insertRow();
            rs.moveToCurrentRow();
            ConnectionDB.getConnection().commit();
        } catch (SQLException ex) {
            handleSQLException(205);
        }
    }

    public void updateUsuario(Usuario user) throws MyException {
        try {
            // Verificar si el nombre de usuario ya existe
            if (usernameExists(user.getNombreUsuario())) {
                throw new MyException(207); // Código de error para nombre de usuario duplicado
            }

            // Preparar la declaración de actualización
            PreparedStatement updateStmt = ConnectionDB.getConnection().prepareStatement(UPDATE_USER_BY_ID);
            updateStmt.setString(1, user.getNombreUsuario()); // Setear el nuevo nombre de usuario
            updateStmt.setString(2, user.getPassword()); // Setear la nueva contraseña
            updateStmt.setInt(3, user.getUserID()); // Setear el ID del usuario para la condición WHERE

            // Ejecutar la actualización
            int rowsAffected = updateStmt.executeUpdate();

            // Verificar si se actualizó correctamente al menos una fila
            if (rowsAffected > 0) {
                ConnectionDB.getConnection().commit(); // Confirmar los cambios en la base de datos
            } else {
                throw new MyException(207); // Lanzar una excepción si no se actualizó ninguna fila (usuario no encontrado)
            }
        } catch (SQLException ex) {
            handleSQLException(207); // Manejar la excepción SQL
        }
    }

    public List<Playlist> getUsuarioPlaylists(String username) throws MyException {
        int userId = getUserIdByName(username);
        List<Playlist> playlists = new ArrayList<>();
        try {
            st = ConnectionDB.getConnection().prepareStatement(GET_ALL_PLAYLISTS_BY_USER);
            st.setString(1, String.valueOf(userId));
            rs = st.executeQuery();
            while (rs.next()) {
                Playlist current = new Playlist(rs.getString("nombrePlaylist"), userId, rs.getFloat("minutosTotales"));
                current.setPlaylistID(rs.getInt("playlistID"));
                playlists.add(current);
            }
        } catch (SQLException ex) {
            handleSQLException(209);
        }
        System.out.println(playlists.size());
        return playlists;
    }

    public void clearData() {
        closeResultSetAndStatement(rs, st);
    }
}
