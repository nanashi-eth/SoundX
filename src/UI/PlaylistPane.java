package UI;

import UI.CustomComponents.BigIconButton;
import UI.CustomComponents.IconButton;
import UI.CustomComponents.RoundedPanel;
import Utils.FontManager;
import Utils.SimpleTimeFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlaylistPane extends RoundedPanel {
    private IconButton primero;
    private IconButton siguiente;
    private IconButton anterior;
    private IconButton ultimo;
    private JLabel titulo;
    private JLabel duracion;
    public PlaylistPane() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(25,0,20,0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 4;

        // Agregar el botón grande a la izquierda
        BigIconButton bigButton = new BigIconButton("\uf144"); // Aquí debes crear tu clase IconButton personalizada
        bigButton.setPreferredSize(new Dimension(100, 100)); // Establece el tamaño deseado
        add(bigButton, gbc);

        gbc.gridy++; // Incrementa el valor de la fila para colocar los botones pequeños debajo
        gbc.gridwidth = 1;

        primero = new IconButton("\uf049"); // Aquí debes crear tu clase IconButton personalizada
        anterior = new IconButton("\uf048"); // Aquí debes crear tu clase IconButton personalizada
        siguiente = new IconButton("\uf051"); // Aquí debes crear tu clase IconButton personalizada
        ultimo = new IconButton("\uf050"); // Aquí debes crear tu clase IconButton personalizada
        add(primero, gbc);
        gbc.gridx++;
        add(anterior, gbc);
        gbc.gridx++;
        add(siguiente, gbc);
        gbc.gridx++;
        add(ultimo, gbc);

        // Agregar la etiqueta grande a la derecha arriba
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        titulo = new JLabel("User");
        titulo.setMinimumSize(new Dimension(150, 50));
        titulo.setFont(FontManager.cargarFuente("spotify-bold.otf", 16f)); // Establece un tamaño de fuente más grande
        add(titulo, gbc);

        // Agregar la etiqueta pequeña a la derecha abajo
        gbc.gridy = 1;
        duracion = new JLabel("Mins");
        duracion.setFont(FontManager.cargarFuente("spotify.otf", 13f));
        duracion.setMinimumSize(new Dimension(50, 50));
        duracion.setForeground(new Color(186, 175, 161));
        add(duracion, gbc);
    }
    
    public void setTitle(String text){
        this.titulo.setText(text);
    }
    public void setDuracion(double duracion){
        this.duracion.setText(SimpleTimeFormatter.formatMinutes(duracion));
    }

    public void setNext(ActionListener e){
        siguiente.addActionListener(e);
    }
    public void setPrev(ActionListener e){
        anterior.addActionListener(e);
    }
    public void setFirst(ActionListener e){
        primero.addActionListener(e);
    }
    public void setLast(ActionListener e){
        ultimo.addActionListener(e);
    }
}
