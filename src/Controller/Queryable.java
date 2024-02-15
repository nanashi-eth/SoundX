package Controller;

public interface Queryable {

    // Todos los usuarios
    String GET_ALL_USERS = "SELECT * FROM USUARIO";

    // Obtener usuario por nombre
    String GET_USER_BY_NAME = "SELECT * FROM USUARIO WHERE nombreUsuario = ?";

    // Obtener el ID de un usuario por su nombre
    String GET_USER_ID_BY_NAME = "SELECT userID FROM USUARIO WHERE nombreUsuario = ?";

    // Verificar si un nombre de usuario ya existe
    String USER_EXISTS_BY_NAME = "SELECT 1 FROM USUARIO WHERE nombreUsuario = ?";

    // Obtener todas las playlist de un usuario
    String GET_ALL_PLAYLISTS_BY_USER = "SELECT * FROM PLAYLIST WHERE userID = ?";

    // Todas las canciones de cada playlist
    String GET_ALL_SONGS_FROM_PLAYLIST = "SELECT C.* " +
            "FROM CANCION C " +
            "JOIN PLAYLIST_CANCION PC ON " +
            "C.cancionID = PC.cancionID " +
            "WHERE playlistID = ?";

    // Todas las canciones de un autor
    String GET_ALL_SONGS_FROM_AUTHOR = "SELECT C.* " +
            "FROM CANCION C " +
            "JOIN AUTOR A ON " +
            "C.autorID = A.autorID " +
            "WHERE A.autorID = ?";

    // Sumar los minutos de una playlist
    String SUM_ALL_MNTS_FROM_PLAYLIST = "SELECT SUM(minutosTotales) " +
            "FROM PLAYLIST";

    // Sumar el número de canciones de un autor
    String COUNT_ALL_SONGS_FROM_AUTHOR = "SELECT COUNT(*) " +
            "FROM CANCION " +
            "WHERE autorID = ?";

    // Sumar el número de canciones que tiene una playlist
    String COUNT_ALL_SONGS_FROM_PLAYLIST = "SELECT COUNT(*) " +
            "FROM PLAYLIST_CANCION " +
            "WHERE playlistID = ?";
}
