package UI.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class CustomMenu extends JMenu {
    private Color normalBackground = new Color(18, 18, 18);
    private Color selectedBackground = normalBackground.brighter();
    private Color popupBackground = new Color(22, 22, 22);

        public CustomMenu(String str) {
            JMenu menu = new JMenu(str);
            menu.setPreferredSize(new Dimension(30, getHeight()));
            menu.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    menu.setBackground(selectedBackground);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    menu.setBackground(normalBackground);
                }
            });
            menu.setOpaque(true);
}
    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected()) {
            g.setColor(selectedBackground);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 1, getWidth(), getHeight() - 2, 10, 10);
    }

    @Override
    public JPopupMenu getPopupMenu() {
        JPopupMenu menu = super.getPopupMenu();
        menu.setBackground(popupBackground); // Cambiar el color del menú emergente
        return menu;
    }
}
