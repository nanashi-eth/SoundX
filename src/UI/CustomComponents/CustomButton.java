package UI.CustomComponents;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomButton extends JButton {

    private static final Color BUTTON_COLOR = new Color(19, 218, 43);
    private static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
    private static final Color BUTTON_BORDER_COLOR = Color.WHITE; // Color del borde cuando el botón tiene el foco
    private static boolean isFocus = false;
    private Border defaultBorder;

    public CustomButton(String text) {
        super(text);
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBorder(new RoundedBorder(15));
        setBackground(BUTTON_COLOR);
        setForeground(BUTTON_FOREGROUND_COLOR);

        // Añadir oyente para el enfoque del botón
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorderPainted(true);
                repaint(); // Vuelve a dibujar el botón para mostrar el borde de enfoque
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorderPainted(false);
                repaint(); 
            }
        });

    }

    @Override               
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2d.setColor(BUTTON_COLOR);

        // Dibujar el fondo del botón
        if (getModel().isPressed()) {
            g2d.setColor(BUTTON_COLOR.brighter());
        } else if (getModel().isRollover()) {
            g2d.setColor(BUTTON_COLOR.brighter());
        } else {
            g2d.setColor(BUTTON_COLOR);
        }   
        g2d.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 15, 15);

        // Dibujar el texto del botón
        FontMetrics metrics = g2d.getFontMetrics(getFont());
        int x = (getWidth() - metrics.stringWidth(getText())) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent() + 2;
        g2d.setColor(BUTTON_FOREGROUND_COLOR);
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

}
