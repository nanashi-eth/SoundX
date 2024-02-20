package Controller;

public interface Queryable {

    // Todos los usuarios
    String GET_ALL_USERS = "SELECT * FROM USUARIO";
    // Actualizar el nombre y la contraseña de un usuario por su ID
    String UPDATE_USER_BY_ID = "UPDATE USUARIO SET nombreUsuario = ?, password = ? WHERE userID = ?";


    // Obtener usuario por nombre
    String GET_USER_BY_NAME = "SELECT * FROM USUARIO WHERE nombreUsuario = ?";

    // Obtener el ID de un usuario por su nombre
    String GET_USER_ID_BY_NAME = "SELECT userID FROM USUARIO WHERE nombreUsuario = ?";

    // Verificar si un nombre de usuario ya existe
    String USER_EXISTS_BY_NAME = "SELECT 1 FROM USUARIO WHERE nombreUsuario = ?";

    // Obtener todas las playlist de un usuario
    String GET_ALL_PLAYLISTS_BY_USER = "SELECT * FROM PLAYLIST WHERE userID = ?";

    // Insertar una canción en una playlist
    String INSERT_SONG_INTO_PLAYLIST = "INSERT INTO PLAYLIST_CANCION (playlistID, cancionID) VALUES (?, ?)";

    // Eliminar una canción de una playlist
    String DELETE_SONG_FROM_PLAYLIST = "DELETE FROM PLAYLIST_CANCION WHERE playlistID = ? AND cancionID = ?";

    // Actualizar la duración total de una playlist
    String UPDATE_PLAYLIST_DURATION = "SELECT SUM(duracion) AS minutosTotales FROM CANCION WHERE cancionID IN (SELECT cancionID FROM PLAYLIST_CANCION WHERE playlistID = ?)";

    // Actualizar los minutos totales de una playlist específica
    String UPDATE_PLAYLIST_TOTAL_MINUTES = "UPDATE PLAYLIST SET minutosTotales = ? WHERE playlistID = ?";

    String GET_AUTOR_NAME = "SELECT nomAutor FROM AUTOR WHERE autorID = ?";
    String GET_ALL_SONGS_WITH_AUTHOR = "SELECT C.cancionID, C.nombreCancion, C.fecha, A.nomAutor, C.imagen, C.duracion " +
            "FROM CANCION C " +
            "JOIN AUTOR A ON C.autorID = A.autorID";

    // Contar todas las canciones en una playlist específica
    String COUNT_ALL_SONGS_IN_PLAYLIST = "SELECT COUNT(*) FROM PLAYLIST_CANCION WHERE playlistID = ?";

    // Contar todas las canciones en la base de datos
    String COUNT_ALL_SONGS = "SELECT COUNT(*) FROM CANCION";
    
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
