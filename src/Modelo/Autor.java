package Modelo;

import javax.persistence.*;

@Entity
@Table(name = "AUTOR")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autorID")
    private int autorID;

    @Column(name = "nomAutor")
    private String nomAutor;

    public Autor(String nomAutor) {
        this.nomAutor = nomAutor;
    }

    public Autor() {
    }

    public int getAutorID() {
        return autorID;
    }

    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public void setNomAutor(String nomAutor) {
        this.nomAutor = nomAutor;
    }
}
