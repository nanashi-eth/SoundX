package Controller;

import Exceptions.ErrorLogger;
import Exceptions.MyException;
import Modelo.Cancion;
import Modelo.Playlist;
import Modelo.Usuario;
import UI.*;
import UI.CustomComponents.RoundedPanel;
import Utils.ImageManager;
import Utils.SimpleTimeFormatter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Usuario usuarioActual = new Usuario();
    private static List<Playlist> playlists = new ArrayList<>();
    private static List<Cancion> allSongs = new ArrayList<>();
    private final LoginShadowPanel loginShadowPanel;
    private final SoundXFrame ventana;
    private SplitPane vistaPlaylist;
    private PlaylistPane playlistPane;
    private ScrollPaneSongs songs;
    private JPanel playlistPanel;
    private ProfilePane profilePane;
    private JPanel currentPlaylist, currentSong;
    private ProfilePane currentProfile;
    private int currentIndex;
    private MyScrollPane playlistSongs;

    public Controller(SoundXFrame ventana, LoginShadowPanel login) {
        this.ventana = ventana;
        this.loginShadowPanel = login;
        this.loginShadowPanel.setLogin(e -> login());
        this.loginShadowPanel.registrar(e -> addUser());
        this.songs = new ScrollPaneSongs();
        this.ventana.exit(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ErrorLogger.getInstance().generateErrorReport(System.getProperty("user.dir") + "/src/Data/Log.txt");
                ventana.dispose();
                try {
                    ConnectionDB.cerrarConexion();
                } catch (MyException ex) {
                    ventana.mostrarError(ex.getMessage());
                }
            }
        });
    }

    public void login(){
        try {
            // Crear una instancia de UserManager
            UserManager userManager = new UserManager(loginShadowPanel.getUsername().toLowerCase().trim());

            // Llamar al método validateUsuario con los campos de texto
            boolean isValid = userManager.validateUsuario(loginShadowPanel.getUsername().toLowerCase().trim(), loginShadowPanel.getPassword().toLowerCase().trim());

            // Si la validación es exitosa, puedes realizar acciones adicionales aquí
            if (isValid) {
                loginShadowPanel.getParentFrame().mostrarMensaje("Inicio de sesión exitoso");
                usuarioActual.setNombreUsuario(loginShadowPanel.getUsername().toLowerCase().trim());
                usuarioActual.setPassword(loginShadowPanel.getPassword().toLowerCase().trim());
                usuarioActual.setUserID(userManager.getUserIdByName(usuarioActual.getNombreUsuario()));
                usuarioActual.setImagen(userManager.getUserImgByName(usuarioActual.getNombreUsuario()));
                setView();
            } else {
                // Si la validación falla, puedes mostrar un mensaje de error o tomar otras medidas
                loginShadowPanel.getParentFrame().mostrarError("Usuario o contraseña incorrectos");
            }
        } catch (MyException ex) {
            loginShadowPanel.getParentFrame().mostrarError(ex.getMessage());
        }        
    }

    public void updateUserData(String newUsername, String newPassword) {
        // Actualizar los datos del usuario actual
        usuarioActual.setNombreUsuario(newUsername.toLowerCase().trim());
        usuarioActual.setPassword(newPassword.toLowerCase().trim());
    }
    
    public void setView(){
        // Por ejemplo, mostrar un mensaje de éxito o cambiar a otra ventana
        // Actualizar los datos del usuario actual
        ventana.changePanel();
        profilePane = new ProfilePane(ImageManager.cargarImagen(usuarioActual.getUserID() + ".jpg", 100, 100, false));
        profilePane.setName(usuarioActual.getNombreUsuario());
        loadSongs();
        profilePane.cambiarUsuario(e ->updateUserInDatabase(profilePane.getUsername(), profilePane.getPassword()));
        profilePane.cambiarImagen(e ->cambiarFoto());
        loadUserData();
        profilePane.cambiarUsuario(e ->updateUserInDatabase(profilePane.getUsername(), profilePane.getPassword()));
        profilePane.cambiarImagen(e ->cambiarFoto());
    }
    public void updateUserInDatabase(String newUsername, String newPassword) {
        try {
            Usuario temp = new Usuario(newUsername, newPassword);
            temp.setUserID(usuarioActual.getUserID());
            // Crear una instancia de UserManager
            UserManager userManager = new UserManager();

            // Llamar al método updateUserAttributes con los datos del usuario actual
            userManager.updateUsuario(temp);
            updateUserData(temp.getNombreUsuario(), temp.getPassword());
            profilePane.setName(newUsername);
        } catch (MyException ex) {
            // Manejar la excepción si ocurre algún error al actualizar los datos en la base de datos
            ventana.mostrarError(ex.getMessage());
        }
    }
    
    public void cambiarFoto(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Elegir nueva imagen de perfil");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Establecer un filtro de extensión de archivo para permitir solo imágenes
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // El usuario ha seleccionado un archivo
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                ImageManager.sobrescribirImagen(imagePath, usuarioActual.getImagen());
                try {
                    profilePane.refreshProfileImage(usuarioActual.getImagen());
                } catch (IOException e) {
                    ventana.mostrarError("Error al cargar la nueva imagen");
                }
            } catch (IOException e) {
                ventana.mostrarError("Error al guardar la imagen");
            } 
        }
    }

    

    public void loadSongs(){
        try {
            CancionManager cancionManager = new CancionManager();
            allSongs = cancionManager.getAllCanciones();
            updateSongs();
        } catch (MyException e) {
            ventana.mostrarError(e.getMessage());
        }
    }
    private void loadUserData() {
        PlaylistManager playlistManager = new PlaylistManager();
        try {
            UserManager userManager = new UserManager(usuarioActual.getNombreUsuario());
            playlists = userManager.getUsuarioPlaylists(usuarioActual.getNombreUsuario());
        } catch (MyException e) {
            ventana.mostrarError(e.getMessage());
        }
        finally {
            if (playlists != null){
                for (Playlist playlist : playlists) {
                    try {
                        playlist.setCanciones(playlistManager.getCancionesFromPlaylist(playlist.getPlaylistID()));
                    } catch (MyException e) {
                        ventana.mostrarError(e.getMessage());
                    }
                }
                profilePane.setTotalPlaylists(String.valueOf(playlists.size()));
                currentProfile = profilePane;
                currentIndex = playlists.size() - 1;
                playlistPane = vistaPlaylist.getPlaylistPane();
                playlistSongs = vistaPlaylist.getCustomScrollPane();
                playlistSongs.deleteSong(e -> deleteSong());
                currentPlaylist = (JPanel) vistaPlaylist.getRightComponent();
                
                playlistPane.setFirst(e -> {
                    currentIndex = 0;
                    updatePlaylist();
                });
                playlistPane.setNext(e -> {
                    currentIndex = Math.min(currentIndex + 1, playlists.size() -1);
                    updatePlaylist();
                });
                playlistPane.setPrev(e -> {
                    currentIndex = Math.max(currentIndex - 1, 0);
                    updatePlaylist();
                });
                playlistPane.setLast(e -> {
                    currentIndex = playlists.size() - 1;
                    updatePlaylist();
                });
                updatePlaylist();
            }
        }
    }

    public void setVistaPlaylist(SplitPane vistaPlaylist) {
        this.vistaPlaylist = vistaPlaylist;
        SideBar sideBar = this.vistaPlaylist.getSideBar();
        sideBar.profileDist(e -> profile());
        sideBar.playlistDist(e ->playlist());
        sideBar.songDist(e -> songsDist());
        sideBar.acercaDe(e ->{ new AboutDialog(ventana).setVisible(true);});
        sideBar.logout(e -> logout());
        sideBar.delete(e -> deleteUser());
    }

    private void songsDist() {
        RoundedPanel back = new RoundedPanel();
        back.add(songs);
        currentSong = back;
        vistaPlaylist.setDividerLocation(200);
        vistaPlaylist.setRightComponent(currentSong);
    }

    public void playlist() {
        vistaPlaylist.setDividerLocation(200);
        vistaPlaylist.setRightComponent(currentPlaylist);
    }

    public void profile(){
        playlistPanel = (JPanel) vistaPlaylist.getRightComponent();
        vistaPlaylist.setDividerLocation(200);
        playlistPanel.setPreferredSize(new Dimension(600, vistaPlaylist.getLeftComponent().getHeight()));
        vistaPlaylist.setRightComponent(currentProfile);
    }
    
    public void updatePlaylist(){
        playlistPane.setTitle(playlists.get(currentIndex).getNombrePlaylist());
        playlistPane.setDuracion(playlists.get(currentIndex).getMinutosTotales());
        DefaultListModel<String[]> songs = vistaPlaylist.getCustomScrollPane().getListModel();
        songs.removeAllElements();
        for (Cancion cancion: playlists.get(currentIndex).getCanciones()){
            String [] actual = {cancion.getImagen(), cancion.getNombreCancion(), cancion.getAutor(), SimpleTimeFormatter.formatMinutes(cancion.getDuracion())};
            songs.addElement(actual);
        }
    }
    
    public void updateSongs(){
        DefaultListModel<String[]> songs = this.songs.getListModel();
        this.songs.addSong(e -> addSong());
        songs.removeAllElements();
        for (Cancion cancion: allSongs){
            String [] actual = {cancion.getImagen(), cancion.getNombreCancion(), cancion.getAutor(), SimpleTimeFormatter.formatMinutes(cancion.getDuracion()), SimpleTimeFormatter.formatDate(cancion.getFecha(), "DD/MM/YYYY")};
            songs.addElement(actual);
        }
    }


    public void logout() {
        // Limpiar datos del usuario actual
        usuarioActual = new Usuario();
        playlists.clear();
        allSongs.clear();
        ventana.showLoginPanel();
        try {
            ConnectionDB.cerrarConexion();
        } catch (MyException e) {
            ventana.mostrarError(e.getMessage());
        }
    }
    
    public void addSong(){
        String name = this.songs.obtenerNombre();
        Cancion cancion = obtenerCancionPorNombre(allSongs, name);
        System.out.println(name);
        System.out.println(cancion.getCancionID());
        System.out.println(playlists.get(currentIndex).getPlaylistID());
        System.out.println(playlists.get(currentIndex).getCanciones().size());
        if (obtenerCancionPorNombre(playlists.get(currentIndex).getCanciones(), name) != null) {
            ventana.mostrarError("Esa cancion ya pertenece a la playlist");
            return;
        }
        PlaylistManager playlistManager = new PlaylistManager();
        try {
            float nuevosMin = playlists.get(currentIndex).getMinutosTotales() + cancion.getDuracion();
            playlistManager.agregarCancionAPlaylist(playlists.get(currentIndex).getPlaylistID(), cancion.getCancionID(),  nuevosMin);
            playlists.get(currentIndex).getCanciones().add(cancion);
            playlists.get(currentIndex).setMinutosTotales(nuevosMin);
            updatePlaylist();
        } catch (MyException e) {
            ventana.mostrarError("Error al agregar la cancion");
        }
    }
    public void deleteSong(){
        String name = this.playlistSongs.obtenerNombre();
        Cancion cancion = obtenerCancionPorNombre(playlists.get(currentIndex).getCanciones(), name);
        if (!playlists.get(currentIndex).getCanciones().contains(cancion)) {
            ventana.mostrarError("Esa cancion no pertenece a la playlist");
            return;
        }
        
        PlaylistManager playlistManager = new PlaylistManager();
        try {
            float nuevosMin = playlists.get(currentIndex).getMinutosTotales() - cancion.getDuracion();
            playlistManager.eliminarCancionDePlaylist(playlists.get(currentIndex).getPlaylistID(), cancion.getCancionID(),  nuevosMin);
            playlists.get(currentIndex).getCanciones().remove(cancion);
            playlists.get(currentIndex).setMinutosTotales(nuevosMin);
            updatePlaylist();
        } catch (MyException e) {
            ventana.mostrarError("Error al borrar la cancion");
        }
        System.out.println(name);
    }

    // Método para obtener una canción por su nombre de un ArrayList de Canciones
    public Cancion obtenerCancionPorNombre(List<Cancion> listaCanciones, String nombre) {
        for (Cancion cancion : listaCanciones) {
            if (cancion.getNombreCancion().equals(nombre)) {
                return cancion; // Devuelve la canción si se encuentra por su nombre
            }
        }
        return null; // Retorna null si no se encuentra ninguna canción con ese nombre
    }
    
    public void deleteUser(){
        UserManager userManager = null;
        try {
            userManager = new UserManager();
            userManager.deleteUser(usuarioActual.getUserID());
            logout();
        } catch (MyException e) {
            ventana.mostrarError(e.getMessage());
        }
    }
    
    public void addUser(){
        try {
        UserManager userManager = new UserManager();
        Usuario user = new Usuario();
        user.setNombreUsuario(loginShadowPanel.getUsername().toLowerCase().trim());
        user.setPassword(loginShadowPanel.getPassword().toLowerCase().trim());
        userManager.insertUsuario(user);
        System.out.println(user.getNombreUsuario());
        user.setUserID(userManager.getUserIdByName(user.getNombreUsuario()));
        user.setImagen(user.getUserID() + ".jpg");
        playlists = new ArrayList<>();
        usuarioActual = user;
        ventana.mostrarMensaje("Registro exitoso");
        setView();
        loadSongs();
        } catch (MyException e) {
            ventana.mostrarError(e.getMessage());
        }
    }
}
