package UI;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

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

        // Cargar la imagen
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/assets/images/background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(img);
        Image image = icon.getImage().getScaledInstance(getWidth(), getHeight(),  Image.SCALE_SMOOTH);

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


