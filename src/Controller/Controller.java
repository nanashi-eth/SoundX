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
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JPanel currentPlaylist, currentSong, currentProfile;
    private int currentIndex;

    public Controller(SoundXFrame ventana, LoginShadowPanel login) {
        this.ventana = ventana;
        this.loginShadowPanel = login;
        this.loginShadowPanel.setLogin(e -> login());
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
                // Por ejemplo, mostrar un mensaje de éxito o cambiar a otra ventana
                loginShadowPanel.getParentFrame().mostrarMensaje("Inicio de sesión exitoso");
                // Actualizar los datos del usuario actual
                usuarioActual.setNombreUsuario(loginShadowPanel.getUsername().toLowerCase().trim());
                usuarioActual.setPassword(loginShadowPanel.getPassword().toLowerCase().trim());
                usuarioActual.setUserID(userManager.getUserIdByName(usuarioActual.getNombreUsuario()));
                usuarioActual.setImagen(userManager.getUserImgByName(usuarioActual.getNombreUsuario()));
                ventana.changePanel();
                profilePane = new ProfilePane(ImageManager.cargarImagen(usuarioActual.getImagen(), 100, 100, false));
                profilePane.setName(usuarioActual.getNombreUsuario());
                loadUserData();
                loadSongs();
                profilePane.cambiarUsuario(e ->updateUserInDatabase(profilePane.getUsername(), profilePane.getPassword()));
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
        playlistPanel.setPreferredSize(new Dimension(600, playlistPanel.getHeight()));
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
    
}
