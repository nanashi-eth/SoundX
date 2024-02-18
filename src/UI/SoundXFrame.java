package UI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import Controller.Controller;
import UI.CustomComponents.ImageBackgroundPanel;
import Utils.FontManager;
import Utils.ImageManager;

public class SoundXFrame extends JFrame {
    private Controller control;
    public SoundXFrame() {
        setTitle("SoundX");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600); // Tamaño inicial de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        // Cambiar el color por defecto de los JPanel a gris muy oscuro
        UIManager.put("Panel.background", new Color(0, 0, 0));
        UIManager.put("OptionPane.messageForeground", Color.WHITE); // Color del texto en los JOptionPane
        UIManager.put( "Component.focusWidth", 0 );
        UIManager.put( "Button.default.borderWidth", 2 );
        UIManager.put( "TitlePane.foreground", new ColorUIResource(255, 255, 255) );
        UIManager.put( "TitlePane.background", new ColorUIResource(0, 0, 0) );
        UIManager.put( "TitlePane.centerTitle", true );
        UIManager.put( "TitlePane.font", FontManager.cargarFuente("spotify-bold.otf", 13f));
        UIManager.put( "Button.default.focusedBorderColor", new ColorUIResource(255, 255, 255) );
        UIManager.put( "MenuBar.selectionBackground", new ColorUIResource(22, 22, 22));
        UIManager.put( "PopupMenu.background", new ColorUIResource(22, 22, 22));
        UIManager.put( "PopupMenu.foreground", new ColorUIResource(255, 255, 255));
        getRootPane().setBackground(new Color(0, 0, 0));
        
        // Crear el panel de inicio de sesión
        LoginShadowPanel loginPanel = new LoginShadowPanel(this);
        
        Image image = ImageManager.cargarImagen("background.jpg", getWidth(), getHeight());

        // Crear el panel con la imagen dividida
        ImageBackgroundPanel diagonalImagePanel = new ImageBackgroundPanel(image);
        setContentPane(diagonalImagePanel);
        // Configurar el tamaño del panel y agregarlo al centro del JFrame
        loginPanel.setPreferredSize(new Dimension(260, 410)); // Tamaño fijo del panel
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        getContentPane().add(loginPanel, gbc);
        getContentPane().setBackground(new Color(0, 0, 0));
        
        control = new Controller(this, loginPanel);
        setVisible(true);
    }

    
    public void exit(WindowAdapter listener) {
        addWindowListener(listener);
    }
    
    public void changePanel(){
        SplitPane pane = new SplitPane();
        control.setVistaPlaylist(pane);
        setContentPane(pane);
        repaint();
        revalidate();
    }
    public void mostrarSidebar() {
        getContentPane().removeAll();
        getContentPane().add(new SplitPane(), BorderLayout.WEST);
        revalidate();
        repaint();
    }

    // Método para mostrar un mensaje de información
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para mostrar un mensaje de error
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}


