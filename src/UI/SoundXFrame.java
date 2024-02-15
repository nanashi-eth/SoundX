package UI;

import javax.swing.*;
import java.awt.*;
import Utils.ImageManager;

public class SoundXFrame extends JFrame {
    public SoundXFrame() {
        setTitle("SoundX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño inicial de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        // Cambiar el color por defecto de los JPanel a gris muy oscuro
        UIManager.put("Panel.background", new Color(0, 0, 0));
        UIManager.put("OptionPane.messageForeground", Color.WHITE); // Color del texto en los JOptionPane
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

        setVisible(true);
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


