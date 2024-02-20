package UI;

import UI.CustomComponents.*;
import Utils.FontManager;
import Utils.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfilePane extends RoundedPanel {
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JButton button;
    private ImageIcon profileImageIcon;
    private Image img;
    
    private JPanel editPanel;
    private PlaceholderTextField user;
    private PlaceholderPasswordField password, password2;
    private JButton acceptButton = new MyButton("Aceptar");
    private JButton cancelButton;
    private MedIconButton image = new MedIconButton("\ue1b6");;
    private JLabel profileImageLabel;

    public ProfilePane(Image img) {
        setLayout(new GridBagLayout());
        this.img = img;

        // Restricciones para el panel superior (imagen y detalles del perfil)
        GridBagConstraints gbcTopPanel = new GridBagConstraints();
        gbcTopPanel.gridx = 0;
        gbcTopPanel.gridy = 0;
        gbcTopPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcTopPanel.anchor = GridBagConstraints.NORTH;
        gbcTopPanel.weighty = 1.0;
        add(createTopPanel(), gbcTopPanel);
        
        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        setMinimumSize(new Dimension(150, 500));
        setPreferredSize(new Dimension(150, 500));
        setOpaquePanels(false);
        setFontForAllComponents(FontManager.cargarFuente("spotify-bold.otf", 13f));
        descriptionLabel.setForeground(new Color(186, 175, 161));
        nameLabel.setFont(FontManager.cargarFuente("spotify-bold.otf",18f));
        button.addActionListener(e ->{
            if (editPanel == null) {
                addEditPanel();
            }
        });
    }
    public void refreshProfileImage(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/Data/Profile/" + path));
        Image foto = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        // Crear una nueva instancia de ImageIcon con la nueva imagen
        profileImageIcon = new ImageIcon(foto);
        // Actualizar la imagen de la JLabel
        profileImageLabel.setIcon(profileImageIcon);
        profileImageLabel.repaint();
        profileImageLabel.revalidate();
    }
    private JPanel createTopPanel() {
        // Panel superior para la imagen y detalles del perfil
        JPanel topPanel = new JPanel(new BorderLayout());

        // Cargar la imagen del perfil
        profileImageIcon = new ImageIcon(img);
        profileImageLabel = new JLabel(profileImageIcon);
        topPanel.add(profileImageLabel, BorderLayout.EAST);

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

        topPanel.add(detailsPanel, BorderLayout.CENTER);


        // Botón debajo de los detalles del perfil
        button = new MyButton("Editar perfil");
        button.setPreferredSize(new Dimension(300, 35));
        button.setMaximumSize(new Dimension(300, 35));
        JPanel wrapper = new JPanel();
        wrapper.add(button);
        wrapper.setOpaque(false);
        topPanel.add(wrapper, BorderLayout.SOUTH);
        return topPanel;
    }

    // Métodos para establecer el nombre y la descripción del perfil
    public void setName(String name) {
        nameLabel.setText(name);
    }

    public void setTotalPlaylists(String description) {
        descriptionLabel.setText("Playlists: " + description);
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
        if (!component.equals(nameLabel)) {
            component.setFont(font);
        }
    }

    public void addEditPanel() {
        // Crear el panel de edición
        editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.setPreferredSize(new Dimension(100, 180));
        editPanel.setOpaque(false);
        editPanel.add(image);

        // Crear el PlaceholderTextField y los PlaceholderPasswordFields
        user = new PlaceholderTextField(this, "Nuevo Usuario", 15);
        user.getDocument().addDocumentListener(new DocumentListener() {
                                                   @Override
                                                   public void insertUpdate(DocumentEvent e) {
                                                       if (!user.getText().isEmpty()) {
                                                           user.valid();
                                                       }
                                                       acceptButton.setEnabled(checkDatos());
                                                   }

                                                   @Override
                                                   public void removeUpdate(DocumentEvent e) {
                                                       if (!user.getText().isEmpty()) {
                                                           user.valid();
                                                       }
                                                       acceptButton.setEnabled(checkDatos());
                                                   }

                                                   @Override
                                                   public void changedUpdate(DocumentEvent e) {
                                                       // Acción cuando cambia el texto
                                                   }
                                               });
        editPanel.add(user);
        editPanel.add(Box.createVerticalStrut(10)); // Espacio vertical

        password = new PlaceholderPasswordField(this, 15, "Nueva Contraseña");
        password.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!String.valueOf(password.getPassword()).isEmpty() && checkPasswordsMatch()){
                    password2.valid();
                    password.valid();
                }
                acceptButton.setEnabled(checkDatos());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!String.valueOf(password.getPassword()).isEmpty() && checkPasswordsMatch()){
                    password2.valid();
                    password.valid();
                }
                acceptButton.setEnabled(checkDatos());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Acción cuando cambia el texto
            }
        });
        editPanel.add(password);
        editPanel.add(Box.createVerticalStrut(10)); // Espacio vertical

        password2 = new PlaceholderPasswordField(this, 15, "Confirmar contraseña");
        password2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!String.valueOf(password.getPassword()).isEmpty() && checkPasswordsMatch()){
                    password2.valid();
                    password.valid();
                }
                acceptButton.setEnabled(checkDatos());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!String.valueOf(password.getPassword()).isEmpty() && checkPasswordsMatch()){
                    password2.valid();
                    password.valid();
                }
                acceptButton.setEnabled(checkDatos());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Acción cuando cambia el texto
            }
        });
        editPanel.add(password2);
        editPanel.add(Box.createVerticalStrut(10)); // Espacio vertical
        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        acceptButton.setEnabled(false);
        // Crear los botones
        acceptButton.setPreferredSize(new Dimension(150, 35));
        acceptButton.addActionListener(e -> {
            if (checkPasswordsMatch()) {
                // Ambas contraseñas coinciden, puedes realizar la acción deseada
            } 
        });
        buttonPane.add(acceptButton);
        buttonPane.add(Box.createHorizontalStrut(10)); // Espacio vertical

        cancelButton = new MyButton("Cancelar");
        cancelButton.setPreferredSize(new Dimension(150, 35));
        cancelButton.addActionListener(e -> hideEditPanel());
        buttonPane.add(cancelButton);
        editPanel.add(buttonPane);
        // Restricciones para el panel de edición
        GridBagConstraints gbcEditPanel = new GridBagConstraints();
        gbcEditPanel.gridx = 0;
        gbcEditPanel.gridy = 1;
        gbcEditPanel.insets = new Insets(20, 0, 0, 0); // Espacio superior
        gbcEditPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcEditPanel.anchor = GridBagConstraints.CENTER;
        gbcEditPanel.weighty = 1.0;
        // Agregar el panel de edición debajo del botón
        add(editPanel, gbcEditPanel);
        setFontForAllComponents(FontManager.cargarFuente("spotify-bold.otf", 13f));
        image.setFont(FontManager.cargarFuente("icon.otf", 32));
        // Repintar y volver a validar el contenido del JPanel
        revalidate();
        repaint();
    }
    public void cambiarUsuario(ActionListener e){
        acceptButton.addActionListener(e);
    }
    
    public void cambiarImagen(ActionListener e){
        image.addActionListener(e);
    }

    private boolean checkPasswordsMatch() {
        char[] pass1 = String.valueOf(password.getPassword()).toCharArray();
        char[] pass2 = String.valueOf(password2.getPassword()).toCharArray();

        if (pass1.length != pass2.length) {
            return false;
        }

        for (int i = 0; i < pass1.length; i++) {
            if (pass1[i] != pass2[i]) {
                return false;
            }
        }

        return true;
    }
    
    public boolean checkDatos(){
        return !user.getText().isEmpty() && checkPasswordsMatch();
    }


    public void hideEditPanel() {
        if (editPanel != null) {
            remove(editPanel);
            editPanel = null;
            revalidate();
            repaint();
        }
    }
    
    private class MyButton extends CustomButton{
        public MyButton(String text) {
            super(text);
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

                g2d.setColor(new Color(186, 175, 161));

                // Dibujar el fondo del botón
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(186, 175, 161).darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(186, 175, 161).darker());
                } else if (!getModel().isEnabled()) {
                    g2d.setColor(new Color(186, 175, 161).darker().darker());
                } else {
                    g2d.setColor(new Color(186, 175, 161));
                }
                g2d.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 15, 15);

                // Dibujar el texto del botón
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent() + 2;
                g2d.setColor(Color.WHITE);
                g2d.drawString(getText(), x, y);

                g2d.dispose();
            }
        
    }

    // Método para obtener el contenido del campo de usuario
    public String getUsername() {
        if (user != null) {
            return user.getText().toLowerCase().trim();
        }
        return null;
    }

    // Método para obtener el contenido del campo de contraseña
    public String getPassword() {
        if (password != null) {
            return String.valueOf(password.getPassword()).toLowerCase().trim();
        }
        return null;
    }
}