package Modelo;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CANCION")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancionID")
    private int cancionID;

    @Column(name = "nombreCancion")
    private String nombreCancion;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "autorID")
    private int autorID;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "duracion")
    private float duracion;

    public Cancion(String nombreCancion, Date fecha, int autorID, String imagen, float duracion) {
        this.nombreCancion = nombreCancion;
        this.fecha = fecha;
        this.autorID = autorID;
        this.imagen = imagen;
        this.duracion = duracion;
    }
    
    public Cancion(){};

    public int getCancionID() {
        return cancionID;
    }

    public void setCancionID(int cancionID) {
        this.cancionID = cancionID;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAutorID() {
        return autorID;
    }

    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }
}
