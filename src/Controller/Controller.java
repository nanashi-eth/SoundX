package Controller;

import Exceptions.MyException;
import Modelo.Cancion;
import Modelo.Playlist;
import Modelo.Usuario;
import UI.LoginShadowPanel;
import UI.PlaylistPane;
import UI.SoundXFrame;
import UI.SplitPane;
import Utils.SimpleTimeFormatter;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Usuario usuarioActual = new Usuario();
    private static List<Playlist> playlists = new ArrayList<>();
    private final LoginShadowPanel loginShadowPanel;
    private final SoundXFrame ventana;
    private SplitPane vistaPlaylist;
    private PlaylistPane playlistPane;
    private int currentIndex;

    public Controller(SoundXFrame ventana, LoginShadowPanel login) {
        this.ventana = ventana;
        this.loginShadowPanel = login;
        this.loginShadowPanel.setLogin(e -> login());
        this.ventana.exit(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ConnectionDB.deleteDB();
                ventana.dispose();
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
                usuarioActual.setNombreUsuario(loginShadowPanel.getUsername().toLowerCase().trim());
                usuarioActual.setPassword(loginShadowPanel.getPassword().toLowerCase().trim());
                usuarioActual.setUserID(userManager.getUserIdByName(usuarioActual.getNombreUsuario()));
                ventana.changePanel();
                loadUserData();
            } else {
                // Si la validación falla, puedes mostrar un mensaje de error o tomar otras medidas
                loginShadowPanel.getParentFrame().mostrarError("Usuario o contraseña incorrectos");
            }
        } catch (MyException ex) {
            loginShadowPanel.getParentFrame().mostrarError(ex.getMessage());
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
                currentIndex = playlists.size() - 1;
                playlistPane = vistaPlaylist.getPlaylistPane();
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
}
