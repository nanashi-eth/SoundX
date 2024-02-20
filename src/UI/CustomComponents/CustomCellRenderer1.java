package UI.CustomComponents;

import UI.ScrollPaneSongs;
import Utils.FontManager;
import Utils.ImageManager;

import javax.swing.*;
import java.awt.*;

public class CustomCellRenderer1 extends DefaultListCellRenderer {

    private static final int padding = 5;
    private static final int borderRadius = 10;
    private ScrollPaneSongs parent;

    public CustomCellRenderer1(ScrollPaneSongs parent) {
        super();
        this.parent = parent;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        // Verificar si el valor es un array de 4 strings
        if (!(value instanceof String[] data)) {
            throw new IllegalArgumentException("El valor debe ser un array de 4 strings");
        }

        // Formatear los strings con el mismo espaciado que el headerLabel
        String formattedValue = String.format("%-20s %-20s %-20s %-20s", data[1], data[2], data[3], data[4]);

        Color backgroundColor = parent.getmHoveredJListIndex() == index ? new Color(18, 18, 18).brighter().brighter() : new Color(18, 18, 18);

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, formattedValue, index, isSelected, cellHasFocus);

        // No estoy seguro del contenido del índice 0 de los datos, por lo que lo dejé tal como está
        // Si necesita modificar la lógica aquí, por favor, hágamelo saber
        Image img = ImageManager.cargarCover(data[0]);
        ImageIcon icon = new ImageIcon(img);
        label.setIcon(icon);
        label.setFont(FontManager.cargarFuente("spotify-bold.otf", 13f));
        label.setForeground(new Color(167, 167, 167));
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

