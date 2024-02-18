package UI.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private static final int BORDER_RADIUS = 20;
    private static final int PADDING = 2;
    
    public RoundedPanel(LayoutManager layout){
        super(layout);
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Obtener dimensiones del panel
        int width = getWidth();
        int height = getHeight();

        

        // Dibujar el rectángulo con bordes redondeados
        g2d.setColor(new Color(18, 18, 18));
        g2d.fillRoundRect(PADDING, PADDING, width - 2 * PADDING , height - 2 * PADDING , BORDER_RADIUS, BORDER_RADIUS);

        g2d.dispose();
    }

    // Constructor
    public RoundedPanel() {
        setOpaque(false);
    }
}
