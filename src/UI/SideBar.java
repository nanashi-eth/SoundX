package UI;

import UI.CustomComponents.CustomMenuBar;
import UI.CustomComponents.SideBarButton;
import UI.CustomComponents.RoundedPanel;
import Utils.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class SideBar extends RoundedPanel {
    private Font icon = FontManager.cargarFuente("icon.otf", 18f);
    private JButton button1,button2,button3,bottomButton;
    private JMenu menu;
    private JMenuItem menuItem1, menuItem2;
    private CustomMenuBar menuBar;
    public SideBar() {
        setPreferredSize(new Dimension(200, getHeight()));
        setLayout(new BorderLayout());

        // Agrega el menú en la parte superior
        menuBar = new CustomMenuBar();
        menu = new JMenu("\uf0c9");
        menu.setOpaque(false);
        menu.setForeground(Color.WHITE);
        menu.setFont(icon);
        menuItem1 = new JMenuItem("Cerrar Sesion");
        menuItem2 = new JMenuItem("Borrar Usuario");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(Box.createHorizontalStrut(8));
        menuBar.add(menu);
        add(menuBar, BorderLayout.NORTH);

        // Agrega los componentes de la barra lateral
        button1 = new SideBarButton("Perfil", "\uf007", e -> {});
        button2 = new SideBarButton("Playlists", "\uf8c9", e -> {});
        button3 = new SideBarButton("Canciones", "\uf89f", e -> {});

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
        bottomButton = new SideBarButton("Acerca De", "\uf05a", e -> {});
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setOpaque(false);
        wrapper.add(bottomButton);
        wrapper.add(Box.createVerticalStrut(8));
        add(wrapper, BorderLayout.SOUTH);
    }
    
    public void profileDist (ActionListener e){
        button1.addActionListener(e);
    } 
    public void playlistDist (ActionListener e){
        button2.addActionListener(e);
    }
    public void songDist (ActionListener e){
        button3.addActionListener(e);
    }
    public void acercaDe (ActionListener e){
        bottomButton.addActionListener(e);
    }
    public void logout(ActionListener e){menuItem1.addActionListener(e);}
    public void delete(ActionListener e){menuItem2.addActionListener(e);}

    private void saludar() {
        System.out.println("Hola");
    }
}
