package UI.CustomComponents;

import Utils.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IconButton extends JButton {
    protected Font iconFont = FontManager.cargarFuente("icon.otf", 12f);

    public IconButton(String iconUnicode) {
        this.setText(iconUnicode);
        this.setContentAreaFilled(false);
        setFont(iconFont);
        applyHoverEffect();
        this.setForeground(Color.WHITE);
        setMinimumSize(new Dimension(30, 30));
    }

    private void applyHoverEffect() {
        this.setBorderPainted(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setFont(getFont().deriveFont(Font.PLAIN, 17f));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setFont(getFont().deriveFont(Font.PLAIN, 15f));
            }
        });
    }
}
