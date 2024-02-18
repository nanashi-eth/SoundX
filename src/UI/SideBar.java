package UI;

import UI.CustomComponents.CustomMenuBar;
import UI.CustomComponents.SideBarButton;
import UI.CustomComponents.RoundedPanel;
import Utils.FontManager;

import javax.swing.*;
import java.awt.*;


public class SideBar extends RoundedPanel {
    private Font icon = FontManager.cargarFuente("icon.otf", 18f);
    public SideBar() {
        setPreferredSize(new Dimension(200, getHeight()));
        setLayout(new BorderLayout());

        // Agrega el menú en la parte superior
        CustomMenuBar menuBar = new CustomMenuBar();
        JMenu menu = new JMenu("\uf0c9");
        menu.setOpaque(false);
        menu.setForeground(Color.WHITE);
        menu.setFont(icon);
        JMenuItem menuItem1 = new JMenuItem("Cerrar Sesion");
        JMenuItem menuItem2 = new JMenuItem("Borrar Usuario");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(Box.createHorizontalStrut(8));
        menuBar.add(menu);
        add(menuBar, BorderLayout.NORTH);

        // Agrega los componentes de la barra lateral
        JButton button1 = new SideBarButton("Perfil", "\uf007", e -> saludar());
        JButton button2 = new SideBarButton("Playlists", "\uf8c9", e -> saludar());
        JButton button3 = new SideBarButton("Canciones", "\uf89f", e -> saludar());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(Box.createVerticalStrut(20)); 
        add(buttonPanel, BorderLayout.CENTER);

        // Agrega un botón al final de la barra lateral
        JButton bottomButton = new SideBarButton("Acerca De", "\uf05a", e -> saludar());
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setOpaque(false);
        wrapper.add(bottomButton);
        wrapper.add(Box.createVerticalStrut(8));
        add(wrapper, BorderLayout.SOUTH);
    }

    private void saludar() {
        System.out.println("Hola");
    }
}
