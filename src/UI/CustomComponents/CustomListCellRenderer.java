package UI.CustomComponents;

import Utils.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomListCellRenderer extends DefaultListCellRenderer {

    private static final int padding = 5;
    private static final int borderRadius = 10;
    private MyScrollPane parent;
    public CustomListCellRenderer(MyScrollPane parent){
        super() ;
        this.parent = parent;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        // Verificar si el valor es un array de 3 strings
        if (!(value instanceof String[] data)) {
            throw new IllegalArgumentException("El valor debe ser un array de 3 strings");
        }

        // Formatear los strings con el mismo espaciado que el headerLabel
        String formattedValue = String.format("%-25s   %-25s   %-25s", data[1], data[2], data[3]);
        Color backgroundColor = parent.getmHoveredJListIndex()== index ? new Color(18, 18, 18).brighter().brighter() : new Color(18, 18, 18);
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, formattedValue, index, isSelected, cellHasFocus);
        Image img = ImageManager.cargarCover(data[0]);
        ImageIcon icon = new ImageIcon(img);
        label.setIcon(icon);
        label.setIconTextGap(85);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBorder(BorderFactory.createEmptyBorder(padding, 10, padding, padding));
        label.setForeground(Color.WHITE);
        if (isSelected) {
            label.setBackground(new Color(18, 18, 18).brighter().brighter());
        } else {
            label.setBackground(backgroundColor);
        }
        

        // Personalizar el fondo del label
        label.setOpaque(false); // Hacer que el label sea transparente

        return label;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Dibujar el fondo redondeado
        g2d.setColor(getBackground());
        g2d.fillRoundRect(5, 1, getWidth() - 10, getHeight() - 4, borderRadius, borderRadius);

        super.paintComponent(g2d);

        g2d.dispose();
    }
}

