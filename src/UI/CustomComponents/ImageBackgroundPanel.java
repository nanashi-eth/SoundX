package UI.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class ImageBackgroundPanel extends JPanel {
    private Image backgroundImage;

    public ImageBackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
