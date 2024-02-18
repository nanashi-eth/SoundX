package UI;

import UI.CustomComponents.CustomButton;
import UI.CustomComponents.RoundedPanel;
import Utils.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfilePane extends RoundedPanel {
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JButton button;
    private ImageIcon profileImageIcon;
    private Image img;

    public ProfilePane(Image img) {
        setLayout(new BorderLayout());
        this.img = img;

        // Panel superior para la imagen y detalles del perfil
        JPanel topPanel = new JPanel(new GridBagLayout());
        add(topPanel, BorderLayout.NORTH);

        // Cargar la imagen del perfil
        profileImageIcon = new ImageIcon(img);
        JLabel profileImageLabel = new JLabel(profileImageIcon);
        topPanel.add(profileImageLabel, new GridBagConstraints());

        // Panel para los detalles del perfil
        JPanel detailsPanel = new JPanel();
        detailsPanel.setOpaque(false);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        detailsPanel.setLayout(new GridLayout(2, 1));

        // Label para el nombre
        nameLabel = new JLabel("Nombre de usuario");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
        detailsPanel.add(nameLabel);

        // Label para la descripción
        descriptionLabel = new JLabel("Descripción del perfil");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
        detailsPanel.add(Box.createVerticalStrut(20));
        detailsPanel.add(descriptionLabel);

        topPanel.add(detailsPanel, new GridBagConstraints());

        // Panel inferior vacío
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(0, 250)); // Ajusta la altura según tus necesidades
        add(bottomPanel, BorderLayout.SOUTH);

        // Botón debajo de los detalles del perfil
        button = new CustomButton("Editar perfil");
        button.setPreferredSize(new Dimension(300, 35));
        button.setMaximumSize(new Dimension(300, 35));
        JPanel wrapper = new JPanel();
        wrapper.setMinimumSize(new Dimension(580, 35));
        wrapper.add(button);
        add(wrapper, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        setMinimumSize(new Dimension(150, 500));
        setPreferredSize(new Dimension(150, 500));
        setOpaquePanels(false);
        setFontForAllComponents(FontManager.cargarFuente("spotify-bold.otf", 13f));
        descriptionLabel.setForeground(new Color(186, 175, 161));
        nameLabel.setFont(nameLabel.getFont().deriveFont(18f));
    }

    public void edit(ActionListener e) {
        button.addActionListener(e);
    }

    // Métodos para establecer el nombre y la descripción del perfil
    public void setName(String name) {
        nameLabel.setText(name);
    }

    public void setTotalPlaylists(String description) {
        descriptionLabel.setText("Playlists: " + description);
    }

    public void setImg(Image img) {
        this.profileImageIcon = new ImageIcon(img);
    }

    // Método para hacer opacos todos los paneles internos
    public void setOpaquePanels(boolean opaque) {
        for (Component component : getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                panel.setOpaque(opaque);
            }
        }
    }

    // Método para establecer la fuente de todos los componentes
    public void setFontForAllComponents(Font font) {
        for (Component component : getComponents()) {
            setFontForComponentAndChildren(component, font);
        }
    }

    private void setFontForComponentAndChildren(Component component, Font font) {
        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                setFontForComponentAndChildren(child, font);
            }
        }
        component.setFont(font);
    }
}