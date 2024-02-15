package Modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLAYLIST")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlistID")
    private int playlistID;

    @Column(name = "nombrePlaylist")
    private String nombrePlaylist;

    @Column(name = "userID")
    private int userID;

    @Column(name = "minutosTotales")
    private float minutosTotales;

    @OneToMany(mappedBy = "playlist")
    private List<Cancion> canciones = new ArrayList<>();

    public Playlist(String nombrePlaylist, int userID, float minutosTotales) {
        this.nombrePlaylist = nombrePlaylist;
        this.userID = userID;
        this.minutosTotales = minutosTotales;
    }
    
    public Playlist(){};

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public String getNombrePlaylist() {
        return nombrePlaylist;
    }

    public void setNombrePlaylist(String nombrePlaylist) {
        this.nombrePlaylist = nombrePlaylist;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getMinutosTotales() {
        return minutosTotales;
    }

    public void setMinutosTotales(float minutosTotales) {
        this.minutosTotales = minutosTotales;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
