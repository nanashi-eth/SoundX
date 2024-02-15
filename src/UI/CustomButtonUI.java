package UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomButtonUI extends BasicButtonUI {
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 30;
    private static final int HOVER_LINE_HEIGHT = 2;
    private static final int BORDER_RADIUS = 15; 
    private boolean pressed = false;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        configureButton((AbstractButton) c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        Graphics2D g2d = (Graphics2D) g;
        setupGraphicsContext(g2d);

        RoundRectangle2D shape = createRoundRectangle(c.getWidth(), c.getHeight());

        paintButtonBackground(button, model, g2d, shape);
        super.paint(g2d, c);
        paintHoverLineIfRollover(model, g2d, c.getHeight());
    }

    private void configureButton(AbstractButton button) {
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    private void setupGraphicsContext(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private RoundRectangle2D createRoundRectangle(int width, int height) {
        return new RoundRectangle2D.Float(0, 0, width, height, BORDER_RADIUS, BORDER_RADIUS);
    }

    private void paintButtonBackground(AbstractButton button, ButtonModel model, Graphics2D g2d, RoundRectangle2D shape) {
        if (model.isPressed()) {
            g2d.setColor(button.getBackground().brighter());
        } else if (model.isRollover()) {
            g2d.setColor(button.getBackground().darker());
        } else {
            g2d.setColor(button.getBackground());
        }

        g2d.fill(shape);
    }

    private void paintHoverLineIfRollover(ButtonModel model, Graphics2D g2d, int buttonHeight) {
        if (pressed) {
            g2d.setColor(Color.WHITE); // Puedes ajustar el color de la línea según tus preferencias

            int yOffset = buttonHeight - HOVER_LINE_HEIGHT;
            g2d.fillRect(0, yOffset, g2d.getClipBounds().width, HOVER_LINE_HEIGHT);
        }
    }

    public void setShouldPaintHoverLineAndRepaint(boolean shouldPaintHoverLine) {
        this.pressed = shouldPaintHoverLine; 
    }
    
}
