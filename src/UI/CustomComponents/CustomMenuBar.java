package UI.CustomComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomMenuBar extends JMenuBar {
    public CustomMenuBar() {
        // Configurar el aspecto del menú
        setBorder(new EmptyBorder(5, 0, 0, 0));
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        // Establecer la calidad del renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Dibujar el fondo redondeado
        int width = getWidth();
        int height = getHeight();
        g2d.setColor(new Color(18, 18, 18));
        g2d.fillRoundRect(2, 2, width - 4, height - 1, 15, 15);
        g2d.dispose();
    }

    @Override
    public JMenu add(JMenu menu) {
        // Ajustar la apariencia del menú
        menu.setOpaque(false);
        menu.setBorderPainted(false);
        menu.setFocusPainted(false);
        menu.setForeground(Color.WHITE);

        // Agregar el menú al menú bar
        return super.add(menu);
    }
}

